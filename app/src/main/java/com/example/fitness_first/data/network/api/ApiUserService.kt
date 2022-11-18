package com.example.fitness_first.data.network.api

import com.example.fitness_first.data.network.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiUserService {
    @POST("users/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("users/logout")
    suspend fun logout(): Response<Unit>

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUser>

    @GET("users/current/routines")
    suspend fun getCurrentUserRoutines(@Query("page") page: Int): Response<NetworkPagedContent<NetworkRoutine>>
}
