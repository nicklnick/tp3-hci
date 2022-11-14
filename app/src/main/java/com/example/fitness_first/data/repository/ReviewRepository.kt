package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.Review
import com.example.fitness_first.data.model.Sport
import com.example.fitness_first.data.network.ReviewRemoteDataSource

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ReviewRepository(
    private val remoteDataSource: ReviewRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val reviewMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var reviews: List<Review> = emptyList()

    suspend fun getReviews(routineId: Int, refresh: Boolean = false): List<Review> {
        if (refresh || reviews.isEmpty()) {
            val result = remoteDataSource.getReviews(routineId)
            // Thread-safe write to latestNews
            reviewMutex.withLock {
                this.reviews = result.content.map { it.asModel(routineId) }
            }
        }

        return reviewMutex.withLock { this.reviews }
    }

    suspend fun addReview(routineId: Int , review: Review) : Unit {
        remoteDataSource.addReview(routineId, review.asNetworkModel())

        //TODO: check que no devuelve nada
    }
}