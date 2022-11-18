package com.example.fitness_first.data.network.model

import com.example.fitness_first.data.model.CompleteCycleExercise
import com.google.gson.annotations.SerializedName

data class NetworkCompleteCycleExercise (

    @SerializedName("exercise"    ) var exercise    : NetworkFullExercise,
    @SerializedName("repetitions" ) var repetitions : Int?      = null,
    @SerializedName("order"       ) var order       : Int,
    @SerializedName("metadata"    ) var metadata    : String?   = null,
    @SerializedName("duration"    ) var duration    : Int?      = null

) {
    fun asModel() : CompleteCycleExercise {
        return CompleteCycleExercise(
            duration = duration,
            exercise = exercise.asModel(),
            order = order,
            metadata = metadata,
            repetitions = repetitions
        )
    }
}