package com.example.xampplocal.callback

import com.example.xampplocal.model.User
import com.google.gson.annotations.SerializedName

data class RegisterCallbackResponse(
    var status:String,
    @SerializedName("message")
    var msg:String,
    //var user: User
    var user: User
){
    override fun toString(): String {
        return msg
    }
}