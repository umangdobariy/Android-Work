package com.example.mvplec_46.network

import com.example.mvplec_46.callback.DataRespones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUserList(@Query ("page") page:Int) : Call<DataRespones>
}

