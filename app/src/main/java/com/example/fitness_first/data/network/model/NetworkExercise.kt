package com.example.fitness_first.data.network.model

import com.example.fitness_first.data.model.Exercise
import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkExercise(
    @SerializedName("id"       )
    var id       : Int,
    @SerializedName("name"     )
    var name     : String,
    @SerializedName("detail"   )
    var detail   : String? = null,
    @SerializedName("type"     )
    var type     : String,
    @SerializedName("date"     )
    var date     : Date?    = null,
    @SerializedName("metadata" )
    var metadata : String? = null
){
    fun asModel(): Exercise{
        return Exercise(
            id,
            name,
            detail,
            type,
        )
    }
}

