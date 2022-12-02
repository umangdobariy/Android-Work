package com.example.crude_opretion.modal

import com.google.gson.annotations.SerializedName

data class createcallback(

    var status:String,
    @SerializedName("message")
    var mess:String,
    var user:User
)
