package com.example.fitness_first.data.network.api

import com.example.fitness_first.data.network.model.NetworkCategory
import com.example.fitness_first.data.network.model.NetworkPagedContent
import com.example.fitness_first.data.network.model.NetworkSport
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCategoryService {

    @GET("categories")
    suspend fun getCategories(): Response<NetworkPagedContent<NetworkCategory>>

    @GET("categories")
    suspend fun getCategory(categoryId: Int): Response<NetworkCategory>

    @POST("categories")
    suspend fun addCategory(@Body sport: NetworkCategory): Response<NetworkCategory>
}