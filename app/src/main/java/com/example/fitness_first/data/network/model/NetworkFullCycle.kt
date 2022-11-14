package com.example.fitness_first.data.network.model

import com.example.fitness_first.data.model.FullCycle
import com.google.gson.annotations.SerializedName


class NetworkFullCycle (
    @SerializedName("id"          ) var id          : Int,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("detail"      ) var detail      : String? = null,
    @SerializedName("type"        ) var type        : String,
    @SerializedName("order"       ) var order       : Int,
    @SerializedName("repetitions" ) var repetitions : Int,
    @SerializedName("metadata"    ) var metadata    : String? = null

) {
    fun asModel(): FullCycle {
        return FullCycle(
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
            repetitions = repetitions
        )
    }
}