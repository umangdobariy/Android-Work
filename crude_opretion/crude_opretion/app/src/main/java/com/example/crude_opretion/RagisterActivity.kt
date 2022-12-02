package com.example.crude_opretion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crude_opretion.databinding.ActivityRagisterBinding
import com.example.crude_opretion.modal.createcallback
import com.example.crude_opretion.network.ApiClient
import com.example.crude_opretion.shareprefrence.prefrence
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RagisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRagisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRagisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup.setOnClickListener {

            var name = binding.etName.text.toString().trim()
            var email = binding.etEmail.text.toString().trim()
            var contec = binding.etContect.text.toString().trim()
            var pass = binding.etPass.text.toString().trim()

            createAccount(name,email,contec,pass)

        }
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }




    }

    private fun createAccount(name: String, email: String, contec: String, pass: String) {

        ApiClient.Init().createacc(1,name,email,contec,pass).enqueue(object :Callback<createcallback>{
            override fun onResponse(
                call: Call<createcallback>,
                response: Response<createcallback>
            ) {
                if (response.isSuccessful){
                    response.body()?.let{
                        Toast.makeText(this@RagisterActivity, "${it.mess}", Toast.LENGTH_SHORT).show()

                       if(it.status == "success") {
                           var manger = prefrence(this@RagisterActivity)
                           manger.datasave(it.user)
                           manger.isLogin(true)
                           startActivity(Intent(this@RagisterActivity, homeActivity::class.java))
                           finishAffinity()

                       }
                    }
                }
            }

            override fun onFailure(call: Call<createcallback>, t: Throwable) {

            }

        })

    }
}