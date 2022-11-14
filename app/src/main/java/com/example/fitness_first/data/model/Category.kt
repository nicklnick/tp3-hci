package com.example.fitness_first.data.model

import com.example.fitness_first.data.network.model.NetworkCategory

class Category(
    var id     : Int?    = null,
    var name   : String,
    var detail : String = ""
) {
    fun asNetworkModel(): NetworkCategory{
        return NetworkCategory(id,name,detail)
    }
}