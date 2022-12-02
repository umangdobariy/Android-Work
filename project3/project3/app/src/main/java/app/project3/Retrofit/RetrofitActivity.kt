package app.project3.Retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.project3.R
import app.project3.databinding.ActivityRetrofitBinding
import app.project3.network.ApiClient
import app.project3.network.ApiService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {

    lateinit var binding: ActivityRetrofitBinding
    lateinit var adapter: DataAdapter
    private var dataList = mutableListOf<Data>()
    lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        retrofit = Retrofit.Builder()
//            .baseUrl("https://reqres.in/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        apiService = retrofit.create(ApiService::class.java)

        adapter = DataAdapter(this, dataList)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter

        getUserList1()

    }

    fun init() {
        // dataList.add(Data(1,"email1@gmail.com","M1","M2","R.drawable.ic_launcher_foreground"))
        // dataList.add(Data(1,"email1@gmail.com","M1","M2",""))
        // dataList.add(Data(1,"email1@gmail.com","M1","M2",""))

    }


    fun getUserList1() {

        binding.pbCirlce.visibility = View.VISIBLE

        var req: Call<dataReposponse> = ApiClient.init().getUserList(1)

        req.enqueue(object : Callback<dataReposponse> {
            override fun onResponse(
                call: Call<dataReposponse>,
                response: Response<dataReposponse>
            ) {
                //requst response
                binding.pbCirlce.visibility = View.GONE
                if (response.isSuccessful) {

                    var res = response.body()

                    res?.let {
                        adapter.setItem(it.dataList)
                    }
                }
            }

            override fun onFailure(call: Call<dataReposponse>, t: Throwable) {
                //requst response
                binding.pbCirlce.visibility = View.GONE
            }
        })
    }


}