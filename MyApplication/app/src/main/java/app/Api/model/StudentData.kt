package app.Api.model

import com.google.gson.annotations.SerializedName


data class StudentData (

    @SerializedName("student")
    var student:MutableList<User>
    )