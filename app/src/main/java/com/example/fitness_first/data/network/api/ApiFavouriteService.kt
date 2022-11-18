package com.example.fitness_first.data.network.api

import com.example.fitness_first.data.network.model.NetworkPagedContent
import com.example.fitness_first.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.*

interface ApiFavouriteService {
    @GET("favourites")
    suspend fun getFavourites(@Query("page") page: Int) : Response<NetworkPagedContent<NetworkRoutine>>

    @POST("favourites/{routineId}")
    suspend fun markFavourite(@Path("routineId") routineId: Int) : Response<Unit>

    @DELETE("favourites/{routineId}")
    suspend fun removeFavourtie(@Path("routineId") routineId: Int) : Response<Unit>
}