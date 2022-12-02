package app.project3.network

import app.project3.CallBack.RegisterCallBackResponse
import app.project3.Retrofit.dataReposponse
import app.project3.model.StudentData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUserList(@Query("page") page:Int): Call<dataReposponse>

    @FormUrlEncoded
    @POST("student.php")
    fun CreateAccount(
        @Field("flag") flag:Int,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("mobile") contect:String,
        @Field("password") password:String,
    ):Call<RegisterCallBackResponse>

    @GET("student.php")
    fun getUserData(@Query("flag") flag: Int):Call<StudentData>

    @FormUrlEncoded
    @POST("student.php")
    fun LoginUser(
       @Field("flag") flag: Int,
       @Field("email") email: String,
       @Field("password") password: String
    ):Call<RegisterCallBackResponse>

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
       @Field("mobile") mobile:String
   ):Call<StudentData>


}