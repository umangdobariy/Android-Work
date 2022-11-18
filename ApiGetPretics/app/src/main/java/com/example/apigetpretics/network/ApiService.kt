package com.example.apigetpretics.network


import com.example.apigetpretics.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users?page=2")
    fun getUserList(): Call<DataResponse>
}