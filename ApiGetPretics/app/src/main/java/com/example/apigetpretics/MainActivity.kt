package com.example.apigetpretics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apigetpretics.adapter.RecycleUserAdapter
import com.example.apigetpretics.databinding.ActivityMainBinding
import com.example.apigetpretics.model.DataResponse
import com.example.apigetpretics.model.User
import com.example.apigetpretics.network.ApiClient
import com.example.apigetpretics.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  var userList = mutableListOf<User>()
    private lateinit var madapter: RecycleUserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        madapter = RecycleUserAdapter(this,userList)
        binding.recyclerviewItem.layoutManager = LinearLayoutManager(this)

        binding.recyclerviewItem.adapter = madapter


        getUserList()

    }

    private fun getUserList() {

        binding.progressCircular.visibility = View.VISIBLE

        var res : Call<DataResponse> = ApiClient.init().getUserList()

        res.enqueue(object : Callback<DataResponse>{
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {

                binding.progressCircular.visibility = View.GONE

                if (response.isSuccessful){

                    var ures = response.body()

                    ures?.let {

                        madapter.setItems(it.userList)
                    }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                binding.progressCircular.visibility = View.GONE
            }

        })

    }


}