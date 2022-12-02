import com.example.androidlocalhost.network.callback.RegisterCallbackResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST



interface ApiService {

    @FormUrlEncoded
    @POST("student.php")
    fun createAccount(

        @Field("flag") flag:Int,
        @Field ("name") name:String,
        @Field ("email") email:String,
        @Field("mobile") contact:String
    ) : Call<RegisterCallbackResponse>
}