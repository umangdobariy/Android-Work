package com.example.xampplocal

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.blue
import com.example.retrofitnetwork.database.ApiClient
import com.example.xampplocal.callback.RegisterCallbackResponse
import com.example.xampplocal.databinding.ActivityLoginBinding
import com.example.xampplocal.sharepref.PrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)

        }

        binding.btnLogin.setOnClickListener{

            val email = binding.etEmail.text.toString()
            var pass = binding.etPass.text.toString()

            if(email.isEmpty()){
                binding.etEmail.error ="Enter valid Email"
            }else if(pass.isEmpty()){
                binding.etPass.error = "Please enter valid password"
            }
            else {
               loginUser(email,pass)
            }
        }

    }

    private fun loginUser(email: String,password:String) {


        ApiClient.init().loginAccount(6,email,password).enqueue(object:Callback<RegisterCallbackResponse>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<RegisterCallbackResponse>,
                response: Response<RegisterCallbackResponse>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        try {
                            if(it.user == null){
                                Toast.makeText(applicationContext, it.msg, Toast.LENGTH_SHORT).show()
                                binding.etEmail.text?.clear()// email:
                                binding.etPass.text?.clear() // Pass:
                                binding.etEmail.focusable
                            }else {
                                binding.etEmail.text?.clear()
                                binding.etPass.text?.clear()
                                var manager = PrefManager(applicationContext)
                                manager.setLoginStatus(true)
                                Toast.makeText(applicationContext, it.msg, Toast.LENGTH_SHORT)
                                    .show()
                                startActivity(Intent(applicationContext, HomeActivity::class.java))
                            }
                        }catch (e:Exception){
                            Log.e("TAG", "onResponse: ${e.printStackTrace()}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RegisterCallbackResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}