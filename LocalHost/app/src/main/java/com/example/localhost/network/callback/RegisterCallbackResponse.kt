package com.example.localhost.network.callback

import com.example.localhost.model.User

data class RegisterCallbackResponse(

    var status:String,
    var msg:String,
    var user: User
)
