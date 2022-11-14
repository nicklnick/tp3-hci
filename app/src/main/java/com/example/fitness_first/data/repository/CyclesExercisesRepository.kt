package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.FullCycleExercise
import com.example.fitness_first.data.network.CyclesExercisesRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CyclesExercisesRepository (
    private val remoteDataSource: CyclesExercisesRemoteDataSource
) {
    private var cyclesExerciseMutex = Mutex()
    private var cycleExercises: List<FullCycleExercise> = emptyList()

    suspend fun getCycleExercises(cycleId: Int, refresh: Boolean = false) : List<FullCycleExercise> {
        if(refresh || cycleExercises.isEmpty()) {
            val result = remoteDataSource.getCycleExercises(cycleId)

            cyclesExerciseMutex.withLock {
                this.cycleExercises = result.content.map { it.asModel() }
            }
        }

        return cyclesExerciseMutex.withLock { this.cycleExercises }
    }
}