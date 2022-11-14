package com.example.fitness_first.data.network.api

import com.example.fitness_first.data.network.model.NetworkFullCycleExercise
import com.example.fitness_first.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCyclesExercisesService {
    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId: Int) : Response<NetworkPagedContent<NetworkFullCycleExercise>>
}