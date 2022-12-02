package com.example.get_depedanapilec_38.model

import com.google.gson.annotations.SerializedName

data class City(

    @SerializedName("city_name")
    var name:String
){
    override fun toString(): String {
        return name
    }
}
