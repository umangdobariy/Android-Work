package com.example.androidlocalhost

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidlocalhost.databinding.ActivityMainBinding
import com.example.androidlocalhost.network.callback.RegisterCallbackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.udRegister.setOnClickListener {
          //  Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()

            var name = binding.udName.text.toString().trim()
            var email = binding.udEmail.text.toString().trim()
            var contact = binding.udContact.text.toString().trim()

            createAccount(name,email,contact)
        }
    }

    private fun createAccount(name: String, email: String, contact: String) {


        var callback:Call<RegisterCallbackResponse> = ApiClient.init().createAccount(1,name,email,contact)
        callback.enqueue(object : Callback<RegisterCallbackResponse>{
            override fun onResponse(
                call: Call<RegisterCallbackResponse>,
                response: Response<RegisterCallbackResponse>
            ) {

                if (response.isSuccessful){

                    var res = response.body()
                    res?.let {


                        if (it.status == "success"){
                            // save user object to sharedpreference
                            //navigate to home page
                            Log.d(TAG, "onResponse: $res")
                        }
                        Toast.makeText(applicationContext, it.msg, Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(call: Call<RegisterCallbackResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
}