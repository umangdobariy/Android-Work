package com.example.jsonparshinglec_3536.modal

import com.google.gson.annotations.SerializedName

//data class DataRespones(
//    var page:Int,
//    @SerializedName("per_page")
//    var parPage:Int,
//    var total:Int,
//    @SerializedName("total_pages")
//    var totalPages:Int,
//    var support: Support,
//    @SerializedName("data")
//    var userList: MutableList<User>
//
//)


data class DataRespones(
    var page:Int,
    @SerializedName("per_page")
    var parPage:Int,
    var total:Int,
    @SerializedName("total_pages")
    var totalPages:Int,
    var support: Support,
    @SerializedName("data")
    var userList: ArrayList<User>

)