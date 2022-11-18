package com.example.fitness_first.data.network.api

import com.example.fitness_first.data.network.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiReviewService {

    @GET("reviews/{routineId}")
    suspend fun getReviews(@Path("routineId") routineId: Int, @Query("page") page: Int) : Response<NetworkPagedContent<NetworkUserReview>>

    @POST("reviews/{routineId}")
    suspend fun addReview(@Path("routineId") routineId: Int, @Body review: NetworkReview) : Response<NetworkReview>
}