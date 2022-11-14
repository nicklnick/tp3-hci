package com.example.fitness_first.data.model

import com.example.fitness_first.data.network.model.NetworkCycleExercise

class CycleExercise(
    var orderBy: String,
    var direction: String,
    var content: List<FullCycleExercise>,
    var isLastPage: Boolean
) {
    fun asNetworkModel() : NetworkCycleExercise {
        return NetworkCycleExercise(
            orderBy = orderBy,
            direction = direction,
            content = content,
            isLastPage = isLastPage
        )
    }

}