package app.Api.network

import app.Api.callback.RegisterCallbackResponse
import app.Api.model.StudentData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    @FormUrlEncoded
    @POST("student.php")
    fun CreateAccount(
        @Field("flag") flag: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String,
    ): Call<RegisterCallbackResponse>


    @GET("student.php")
    fun getUserData(@Query("flag") flag: Int):Call<StudentData>

    @FormUrlEncoded
    @POST("student.php")
    fun LoginUser(
        @Field("flag") flag: Int,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<RegisterCallbackResponse>


    @FormUrlEncoded
    @POST("student.php")
    fun DeleteUser(
        @Field("flag") flag: Int,
        @Field("id") Id: String,
    ):Call<StudentData>

    @FormUrlEncoded
    @POST("student.php")
    fun UpdateData(
        @Field("flag") flag: Int,
        @Field("id") Id: String,
        @Field("name")  name: String,
        @Field("email") email:String,
        @Field("mobile") mobile:String,
        ):Call<StudentData>

}