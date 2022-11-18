package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.CompleteCycleExercise
import com.example.fitness_first.data.network.CyclesExercisesRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CyclesExercisesRepository (
    private val remoteDataSource: CyclesExercisesRemoteDataSource
) {
    private var cyclesExerciseMutex = Mutex()
    private var cycleExercises: List<CompleteCycleExercise> = emptyList()

    suspend fun getCycleExercises(cycleId: Int, refresh: Boolean = false) : List<CompleteCycleExercise> {
        if(refresh || cycleExercises.isEmpty()) {
            val result = remoteDataSource.getCycleExercises(cycleId)

            cyclesExerciseMutex.withLock {
                this.cycleExercises = result.content.map { it.asModel() }
            }
        }

        return cyclesExerciseMutex.withLock { this.cycleExercises }
    }
}