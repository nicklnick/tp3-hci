package com.example.fitness_first.data.model

import com.example.fitness_first.data.network.model.NetworkFullCycleExercise

class FullCycleExercise (
    var exercise: FullExercise,
    var order: Int,
    var duration: Int? = null,
    var repetitions: Int? = null,
    var metadata: String? = null
) {
    fun asNetworkModel() : NetworkFullCycleExercise {
        return NetworkFullCycleExercise(
            exercise = exercise.asNetworkModel(),
            order = order,
            duration = duration,
            repetitions = repetitions,
            metadata = metadata
        )
    }
}