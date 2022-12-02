package com.example.crude_opretion.network

import com.example.crude_opretion.modal.User
import com.example.crude_opretion.modal.createcallback
import com.example.crude_opretion.modal.studentlist
import retrofit2.Call
import retrofit2.http.*

interface ApiServies {

    // post register mate ni query
    @FormUrlEncoded
    @POST("student.php")
    fun createacc(
        @Field("flag") flag: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Call<createcallback>


    //Fromurlencode only use in post request
    //get request
    @GET("student.php")
    fun getstudentlist(
        @Query("flag") flag: Int
    ): Call<studentlist>

    //update  mate query
    @FormUrlEncoded
    @POST("student.php")
    fun updatedata(
        @Field("flag") flag: Int,
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("mobile") mobile: String
    ): Call<studentlist>

    //delate mate
    @FormUrlEncoded
    @POST("student.php")
    fun delatedata(
        @Field("flag") flag: Int,
        @Field("id") id: String
    ): Call<studentlist>

    //login
    @FormUrlEncoded
    @POST("student.php")
    fun logindata(
        @Field("flag") flag: Int,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<createcallback>


}