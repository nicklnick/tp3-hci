package com.example.fitness_first.data.network

import com.example.fitness_first.data.network.api.ApiReviewService
import com.example.fitness_first.data.network.model.NetworkPagedContent
import com.example.fitness_first.data.network.model.NetworkReview
import com.example.fitness_first.data.network.model.NetworkUserReview


class ReviewRemoteDataSource(
    private val apiReviewService : ApiReviewService
) : RemoteDataSource()
{
    suspend fun getReviews(routineId: Int) : NetworkPagedContent<NetworkUserReview> {
        return handleApiResponse {
            apiReviewService.getReviews(routineId)
        }
    }

    suspend fun addReview(routineId: Int, networkReview: NetworkReview) : NetworkReview {
        return handleApiResponse {
            apiReviewService.addReview(routineId, networkReview)
        }
    }
}