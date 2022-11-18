package com.example.fitness_first.data.network.model

import com.example.fitness_first.data.model.CompleteCycle
import com.google.gson.annotations.SerializedName


class NetworkCompleteCycle (
    @SerializedName("id"          ) var id          : Int,
    @SerializedName("type"        ) var type        : String,
    @SerializedName("order"       ) var order       : Int,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("detail"      ) var detail      : String? = null,
    @SerializedName("repetitions" ) var repetitions : Int,
    @SerializedName("metadata"    ) var metadata    : String? = null

) {
    fun asModel(): CompleteCycle {
        return CompleteCycle(
            id = id,
            order = order,
            name = name,
            type = type,
            repetitions = repetitions,
            detail = detail
        )
    }
}