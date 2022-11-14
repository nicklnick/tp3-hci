package com.example.fitness_first.data.model

import com.example.fitness_first.data.network.model.NetworkFullCycle

class FullCycle(
    var id: Int,
    var name: String,
    var detail: String? = null,
    var type: String,
    var order: Int,
    var repetitions: Int
) {
    fun asNetworkModel() : NetworkFullCycle {
        return NetworkFullCycle(
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
            repetitions = repetitions
        )
    }
}