package com.example.fitness_first.data.model

import com.example.fitness_first.data.network.model.NetworkReview

class Review(
    val score: Int,
    val userId: Int? = null,
    val routineId: Int? = null,
    val review: String = "",
) {

    fun asNetworkModel() : NetworkReview{
        return NetworkReview(score = score, review = review)
    }
}