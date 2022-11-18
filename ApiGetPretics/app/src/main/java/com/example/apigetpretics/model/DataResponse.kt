package com.example.apigetpretics.model

import com.google.gson.annotations.SerializedName


data class DataResponse(

    var page:Int,
    @SerializedName("per_page")
    var perPage:Int,
    var total:Int,
    @SerializedName("total_pages")
    var totalPages:Int,
    var support: Support,
    @SerializedName("data")
    var userList:MutableList<User>

)
