


import com.example.localhost.network.callback.RegisterCallbackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*


interface ApiService{


    @FormUrlEncoded
    @POST("student.php")
    fun createAccount(

        @Field("flag") flag:Int,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("mobile") contact:String

    ) : Call<RegisterCallbackResponse>
}