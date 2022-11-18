package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.CompleteCycle
import com.example.fitness_first.data.network.RoutinesCyclesRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutinesCyclesRepository(
    private val remoteDataSource: RoutinesCyclesRemoteDataSource
) {
    private var routinesCyclesMutex = Mutex()
    private var routineCycles: List<CompleteCycle> = emptyList()

    suspend fun getRoutineCycles(routineId: Int, refresh: Boolean = false) : List<CompleteCycle> {
        var page = 0
        if(refresh || routineCycles.isEmpty()) {
            this.routineCycles = emptyList()
            do {
                val result = remoteDataSource.getRoutinesCycles(routineId, page)
                routinesCyclesMutex.withLock {
                    this.routineCycles = this.routineCycles.plus(result.content.map { it.asModel() })
                }
                page++
            } while(!result.isLastPage)
        }

        return routinesCyclesMutex.withLock { this.routineCycles }
    }
}