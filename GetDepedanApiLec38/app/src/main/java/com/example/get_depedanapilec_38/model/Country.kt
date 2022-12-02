package com.example.get_depedanapilec_38.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country_name")
    var name:String,
    @SerializedName("country_short_name")
    var shortName:String,
    @SerializedName("country_phone_code")
    var phoneCode:Int,
){
    override fun toString(): String {
        return name
    }
}
