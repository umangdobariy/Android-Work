package com.example.jsonparshinglec_3536.network

import com.example.jsonparshinglec_3536.modal.DataRespones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUserList(@Query("page")page:Int) : Call<DataRespones>

}