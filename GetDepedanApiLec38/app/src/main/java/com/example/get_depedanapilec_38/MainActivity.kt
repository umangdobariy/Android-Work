package com.example.get_depedanapilec_38

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast


import com.example.get_depedanapilec_38.databinding.ActivityMainBinding
import com.example.get_depedanapilec_38.model.City
import com.example.get_depedanapilec_38.model.Country
import com.example.get_depedanapilec_38.model.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadCountry()

    }

    private fun loadCountry() {

        ApiClient.init().getCountryList("Bearer ${Const.TOKEN}").enqueue(object : Callback<MutableList<Country>>{
            override fun onResponse(
                call: Call<MutableList<Country>>,
                response: Response<MutableList<Country>>
            ) {

                    if (response.isSuccessful){

                    if (response.body()!=null){

                        var countryAdapter = ArrayAdapter(
                            applicationContext,
                            R.layout.simple_spinner_dropdown_item,
                            response.body()!!
                        )
                        binding.autoCountry.setAdapter(countryAdapter)

                    }

                        binding.autoCountry.setOnItemClickListener { adapterView, view, pos, l ->

                            var country = response.body()!![pos]
                            getStateList(country)
                        }



                }
            }

            override fun onFailure(call: Call<MutableList<Country>>, t: Throwable) {

            }

        })
    }

    private fun getStateList(country: Country) {

        ApiClient.init().getStateList("Bearer ${Const.TOKEN}",country.name).enqueue(object : Callback<MutableList<State>>{
            override fun onResponse(
                call: Call<MutableList<State>>,
                response: Response<MutableList<State>>
            ) {
                if (response.isSuccessful){

                    if (response.body()!=null){

                        var stateAdapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            response.body()!!
                        )
                        binding.autoState.setAdapter(stateAdapter)

                    }

                    binding.autoState.setOnItemClickListener { adapterView, view, pos, l ->

                        var state = response.body()!![pos]
                        getCityList(state)

                    }

                }
            }

            override fun onFailure(call: Call<MutableList<State>>, t: Throwable) {
                Log.d("", "onFailure: ")
            }

        })
    }

    private fun getCityList(state: State) {

        ApiClient.init().getCityList("Bearer ${Const.TOKEN}",state.name).enqueue(object : Callback<MutableList<City>>{
            override fun onResponse(
                call: Call<MutableList<City>>,
                response: Response<MutableList<City>>
            ) {
                if (response.isSuccessful){


                    if (response.body()!=null){

                        var cityAdapter = ArrayAdapter(
                            applicationContext,
                            R.layout.simple_spinner_dropdown_item,
                            response.body()!!
                        )
                        binding.autoCity.setAdapter(cityAdapter)

                    }
                    binding.autoCity.setOnItemClickListener { adapterView, view, pos, l ->

                        var city = response.body()!![pos]
                       // getCityList(state)
                        Toast.makeText(this@MainActivity, "$city", Toast.LENGTH_SHORT).show()

                    }

                }
            }

            override fun onFailure(call: Call<MutableList<City>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}