package com.example.retrofitnetwork.database


import com.example.xampplocal.callback.RegisterCallbackResponse
import com.example.xampplocal.model.Student
import com.example.xampplocal.model.User
import retrofit2.Call
import retrofit2.http.*


interface Service {

    @FormUrlEncoded
    @POST("student.php")
    fun createAccount(
        @Field("flag") flag:Int,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("mobile") contact:String,
        @Field("password") password:String
    ): Call<RegisterCallbackResponse>

    @FormUrlEncoded
    @POST("student.php")
    fun loginAccount(
        @Field("flag") flag:Int,
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<RegisterCallbackResponse>

    @GET("student.php")
    fun getUserData(
        @Query("flag") flag:Int
    ):Call<Student>

    @FormUrlEncoded
    @POST("student.php")
    fun deleteUserData(
        @Field("flag") flag:Int,
        @Field("id") id:String
    ):Call<Student>

    @FormUrlEncoded
    @POST("student.php")
    fun undoUserData(
        @Field("flag") flag:Int,
        @Field("id") id:String
    ):Call<Student>

    @FormUrlEncoded
    @POST("student.php")
    fun updateUserData(
        @Field("flag") flag:Int,
        @Field("id") id:String,
        @Field("name") name:String,
        @Field("mobile") mobile:String
    ):Call<Student>


}