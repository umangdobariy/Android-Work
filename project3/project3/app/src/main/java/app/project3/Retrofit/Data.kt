package app.project3.Retrofit

import com.google.gson.annotations.SerializedName

data class Data(
    var id:Int,
    var email:String,
    @SerializedName("first_name")
    var FirstName:String,
    @SerializedName("last_name")
    var LastName:String,
    var avatar:String="") {

}