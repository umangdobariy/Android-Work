package com.example.getpretics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getpretics.adapter.RecycleUserAdapter
import com.example.getpretics.databinding.ActivityMainBinding
import com.example.getpretics.databinding.CustomUserLayoutBinding
import com.example.getpretics.model.DataResponse
import com.example.getpretics.model.User
import com.example.getpretics.network.ApiClient
import com.example.getpretics.network.ApiService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecycleUserAdapter
    private  var userList = mutableListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        adapter = RecycleUserAdapter(this,userList)

        var manger = LinearLayoutManager(applicationContext)
        binding.recyclerItem.layoutManager = manger
        binding.recyclerItem.adapter = adapter

        getUserList()

    }

    private fun getUserList() {


        var res : Call<DataResponse> = ApiClient.init().getUserList()

        res.enqueue(object : Callback<DataResponse>{
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {

                if (response.isSuccessful){

                   var mumang =  response.body()

                    mumang?.let {

                        adapter.setItems(it.userList)
                    }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}