package com.example.crude_opretion.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{
        private var retrofit: Retrofit? = null


        fun Init():ApiServies {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.29.34/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    }
                return retrofit!!.create(ApiServies::class.java)
            }


    }

}