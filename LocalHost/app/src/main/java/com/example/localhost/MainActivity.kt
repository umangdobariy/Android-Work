package com.example.localhost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.localhost.databinding.ActivityMainBinding
import com.example.localhost.network.callback.RegisterCallbackResponse
import com.example.localhost.pref.PrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.udRegister.setOnClickListener {

            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
            var name = binding.udName.text.toString().trim()
            var email = binding.udEmail.text.toString().trim()
            var contact = binding.udContact.text.toString().trim()

            createAccount(name,email,contact)

        }
    }

    override fun onResume() {
        super.onResume()

        if (PrefManager(this).getLoginstatus() ){

            startActivity(Intent(applicationContext,HomeAcitivity::class.java))
            finish()
        }
    }

    private fun createAccount(name: String, email: String, contact: String) {

         ApiClient.init().createAccount(1,name,email,contact)
        .enqueue(object : Callback<RegisterCallbackResponse>{
            override fun onResponse(
                call: Call<RegisterCallbackResponse>,
                response: Response<RegisterCallbackResponse>
            ) {

                if (response.isSuccessful){

                    var res = response.body()
                    res?.let {

                        Toast.makeText(applicationContext, it.msg, Toast.LENGTH_SHORT).show()

                        if (it.status == "success"){
                            // save user object to sharedpreference
                            // navigate to home page

                            var manager = PrefManager(applicationContext)
                            manager.setLoginStatus(true)
                            manager.setUser(it.user)

                        }

                    }
                }
            }

            override fun onFailure(call: Call<RegisterCallbackResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.toString()}")
            }

        })
    }


}