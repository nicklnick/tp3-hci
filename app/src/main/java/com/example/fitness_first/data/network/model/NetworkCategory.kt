package com.example.fitness_first.data.network.model

import com.google.gson.annotations.SerializedName


class NetworkCategory (

    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("detail" ) var detail : String? = null

)