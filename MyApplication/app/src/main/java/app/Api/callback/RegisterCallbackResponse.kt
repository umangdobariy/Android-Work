package app.Api.callback

import app.Api.model.User
import com.google.gson.annotations.SerializedName

data class RegisterCallbackResponse(

    @SerializedName("status")
    var status:String,
    @SerializedName("message")
    var message:String,
    var user: User
)
