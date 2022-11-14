package com.example.fitness_first.data.model

import com.example.fitness_first.data.network.model.NetworkFullExercise
import java.util.*

class FullExercise(
    var id: Int,
    var name: String,
    var detail: String? = null,
    var type: String,
    var date: Date? = null,
    var order: Int
) {
    fun asNetworkModel(): NetworkFullExercise {
        return NetworkFullExercise (
            id = id,
            name = name,
            detail = detail,
            type = type,
            date = date,
            order = order
        )
    }
}