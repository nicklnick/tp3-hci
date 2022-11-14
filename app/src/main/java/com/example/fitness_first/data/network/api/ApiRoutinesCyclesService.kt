package com.example.fitness_first.data.network.api

import com.example.fitness_first.data.network.model.NetworkFullCycle
import com.example.fitness_first.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRoutinesCyclesService {
    @GET("routines/{routineId}/cycles")
    suspend fun getRoutinesCycles(@Path("routineId") routineId: Int) : Response<NetworkPagedContent<NetworkFullCycle>>
}