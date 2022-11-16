package com.example.fitness_first.data.model

import com.example.fitness_first.data.network.model.NetworkCategory
import com.example.fitness_first.data.network.model.NetworkRoutine
import com.example.fitness_first.data.network.model.NetworkUser
import java.util.*

class Routine (
    var id: Int,
    var name: String,
    var detail: String? = null,
    var date: Date? = null,
    var score: Int? = null,
    var difficulty: String? = null,
    var user: NetworkUser? = null,
    var category: NetworkCategory,
    var liked: Boolean = false,
    var fromCUser: Boolean = false
) {
    fun asNetworkModel(): NetworkRoutine{
        return NetworkRoutine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            difficulty = difficulty,
            user = user,
            category = category
        )
    }
}