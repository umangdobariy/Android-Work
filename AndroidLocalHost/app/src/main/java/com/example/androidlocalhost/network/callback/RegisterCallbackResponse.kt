package com.example.androidlocalhost.network.callback

import com.google.gson.annotations.SerializedName

data class RegisterCallbackResponse(

    @SerializedName("status")
    var status:String,
    @SerializedName("message")
    var msg:String,

)
