package com.example.getpretics.model

import com.google.gson.annotations.SerializedName

data class User(

    var id:Int,
    var email:String,
    @SerializedName("first_name")
    var fName:String,
    @SerializedName("last_name")
    var lName:String,
    var avatar:String = ""
)
