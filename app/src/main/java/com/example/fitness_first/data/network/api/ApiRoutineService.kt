package com.example.fitness_first.data.network.api

import com.example.fitness_first.data.network.model.NetworkPagedContent
import com.example.fitness_first.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRoutineService {
    @GET("routines")
    suspend fun getRoutines() : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines")
    suspend fun getRoutinesWFilter(@Query("orderBy") orderBy: String, @Query("direction") direction: String) : Response<NetworkPagedContent<NetworkRoutine>>


    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>
}