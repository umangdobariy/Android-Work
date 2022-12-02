package com.example.getpretics.network

import com.example.getpretics.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {


    @GET("users?page=2")
    fun getUserList(): Call<DataResponse>
}