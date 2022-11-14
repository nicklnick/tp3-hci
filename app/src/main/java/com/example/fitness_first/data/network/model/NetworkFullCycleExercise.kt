package com.example.fitness_first.data.network.model

import com.example.fitness_first.data.model.FullCycleExercise
import com.google.gson.annotations.SerializedName

data class NetworkFullCycleExercise (

    @SerializedName("exercise"    ) var exercise    : NetworkFullExercise,
    @SerializedName("order"       ) var order       : Int,
    @SerializedName("duration"    ) var duration    : Int?      = null,
    @SerializedName("repetitions" ) var repetitions : Int?      = null,
    @SerializedName("metadata"    ) var metadata    : String?   = null

) {
    fun asModel() : FullCycleExercise {
        return FullCycleExercise(
            exercise = exercise.asModel(),
            order = order,
            duration = duration,
            repetitions = repetitions,
            metadata = metadata
        )
    }
}