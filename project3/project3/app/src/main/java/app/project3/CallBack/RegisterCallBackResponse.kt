package app.project3.CallBack

import app.project3.model.User
import com.google.gson.annotations.SerializedName

data class RegisterCallBackResponse(

    @SerializedName("status")
    var status:String,
    @SerializedName("message")
    var message:String,
    var user: User

 ) {


}