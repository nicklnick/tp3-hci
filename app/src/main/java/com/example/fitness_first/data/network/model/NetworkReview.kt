package com.example.fitness_first.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkReview (
    @SerializedName("id"     )
    var id     : Int? = null,
    @SerializedName("date"   )
    var date   : Date?    = null,
    @SerializedName("score"  )
    var score  : Int,
    @SerializedName("review" )
    var review : String? = null
)

