

import com.example.get_depedanapilec_38.model.City
import com.example.get_depedanapilec_38.model.Country
import com.example.get_depedanapilec_38.model.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path


interface ApiService {

    @GET("countries")
    fun getCountryList(@Header("Authorization") token:String): Call<MutableList<Country>>


    @GET("states/{con}")
    fun getStateList(@Header("Authorization") token: String,@Path("con") country:String) : Call<MutableList<State>>


    @GET("cities/{uon}")
    fun getCityList(@Header("Authorization")token: String,@Path("uon")state:String) : Call<MutableList<City>>


}