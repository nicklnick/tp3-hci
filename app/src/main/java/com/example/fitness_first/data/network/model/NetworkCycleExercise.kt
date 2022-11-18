package com.example.fitness_first.data.network.model

import com.example.fitness_first.data.model.CycleExercise
import com.example.fitness_first.data.model.CompleteCycleExercise
import com.google.gson.annotations.SerializedName


data class NetworkCycleExercise (

    @SerializedName("totalCount" ) var totalCount : Int?                    = null,
    @SerializedName("orderBy"    ) var orderBy    : String,
    @SerializedName("direction"  ) var direction  : String,
    @SerializedName("content"    ) var content    : List<CompleteCycleExercise> = emptyList(),
    @SerializedName("size"       ) var size       : Int?                    = null,
    @SerializedName("page"       ) var page       : Int?                    = null,
    @SerializedName("isLastPage" ) var isLastPage : Boolean

) {
    fun asModel() : CycleExercise {
        return CycleExercise(
            orderBy = orderBy,
            direction = direction,
            content = content,
            isLastPage = isLastPage
        )
    }
}