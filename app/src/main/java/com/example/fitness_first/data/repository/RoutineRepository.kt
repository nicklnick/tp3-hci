package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.Routine
import com.example.fitness_first.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository (
    private val remoteDataSource: RoutineRemoteDataSource
) {
    private val routinesMutex = Mutex()
    private var routines: List<Routine> = emptyList()

    suspend fun getRoutines(refresh: Boolean = false): List<Routine> {
        var page = 0
        if( refresh || routines.isEmpty()){
            this.routines = emptyList()
            do {
                val result = remoteDataSource.getRoutines(page)
                routinesMutex.withLock {
                    this.routines = this.routines.plus(result.content.map { it.asModel() })
                }
                page++
            } while(!result.isLastPage)
        }
        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutinesWFilter(order: String, dir: String): List<Routine> {
        var page = 0
        this.routines = emptyList()

        do {
            val filteredRoutines = remoteDataSource.getRoutinesWFilter(page, order, dir)
            routinesMutex.withLock {
                this.routines = this.routines.plus(filteredRoutines.content.map { it.asModel() })
            }
            page++
        } while(!filteredRoutines.isLastPage)

        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine {
        return remoteDataSource.getRoutine(routineId).asModel()
    }
}