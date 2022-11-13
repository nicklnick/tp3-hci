package com.example.fitness_first.data.network

import com.example.fitness_first.data.network.api.ApiExerciseService
import com.example.fitness_first.data.network.model.NetworkExercise
import com.example.fitness_first.data.network.model.NetworkPagedContent
import com.example.fitness_first.data.network.model.NetworkSport

class ExerciseRemoteDataSource(
    private val apiExerciseService: ApiExerciseService
): RemoteDataSource() {

    suspend fun getExercises(): NetworkPagedContent<NetworkExercise>{
        return handleApiResponse {
            apiExerciseService.getExercises()
        }
    }
    suspend fun getExercise(exerciseId: Int) : NetworkExercise {
        return handleApiResponse {
            apiExerciseService.getExercise(exerciseId)
        }
    }
}