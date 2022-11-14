package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.FullCycle
import com.example.fitness_first.data.network.RoutinesCyclesRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutinesCyclesRepository(
    private val remoteDataSource: RoutinesCyclesRemoteDataSource
) {
    private var routinesCyclesMutex = Mutex()
    private var routineCycles: List<FullCycle> = emptyList()

    suspend fun getRoutineCycles(routineId: Int, refresh: Boolean = false) : List<FullCycle> {
        if(refresh || routineCycles.isEmpty()) {
            val result = remoteDataSource.getRoutinesCycles(routineId)

            routinesCyclesMutex.withLock {
                this.routineCycles = result.content.map { it.asModel() }
            }
        }

        return routinesCyclesMutex.withLock { this.routineCycles }
    }
}