package com.example.fitness_first.data.network

import com.example.fitness_first.data.network.api.ApiCyclesExercisesService
import com.example.fitness_first.data.network.model.NetworkCompleteCycleExercise
import com.example.fitness_first.data.network.model.NetworkPagedContent

class CyclesExercisesRemoteDataSource(
    private val cyclesExercisesService: ApiCyclesExercisesService
) : RemoteDataSource() {

    suspend fun getCycleExercises(cycleId: Int) : NetworkPagedContent<NetworkCompleteCycleExercise> {
        return handleApiResponse {
            cyclesExercisesService.getCycleExercises(cycleId)
        }
    }
}