package com.example.mvplec_46.callback

import com.example.jsonparshinglec_3536.modal.Support
import com.example.jsonparshinglec_3536.modal.User
import com.google.gson.annotations.SerializedName




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