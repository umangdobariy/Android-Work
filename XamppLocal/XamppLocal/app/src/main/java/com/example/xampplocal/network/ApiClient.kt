package com.example.retrofitnetwork.database

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{

        private var retrofit: Retrofit?=null

        fun init() : Service
        {
            if(retrofit==null){

                retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.29.34/api/")//192.168.56.32
                    .addConverterFactory(GsonConverterFactory.create())//192.168.0.105
                    .build()
            }

            return retrofit!!.create(Service::class.java)
        }


    }
}