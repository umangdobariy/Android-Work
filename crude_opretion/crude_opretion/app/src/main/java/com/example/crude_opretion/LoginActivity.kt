package com.example.crude_opretion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.crude_opretion.databinding.ActivityLoginBinding
import com.example.crude_opretion.modal.createcallback
import com.example.crude_opretion.modal.studentlist
import com.example.crude_opretion.network.ApiClient
import com.example.crude_opretion.shareprefrence.prefrence
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //navigrat to Ragister Activity
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(applicationContext,RagisterActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {

            var email = binding.etEmail.text.toString().trim()
            var pass = binding.etPass.text.toString().trim()

            if (email.isEmpty()){
              seterror(binding.etEmail, "Enter Email")
            }else if (pass != "123456" ) {
                seterror(binding.etPass, "Enter password")
            }
            else{
                createlogin(email,pass)
            }

        }
    }

    private fun createlogin(email: String, pass: String) {
        ApiClient.Init().logindata(6,email,pass).enqueue(object : Callback<createcallback>{
            override fun onResponse(call: Call<createcallback>, response: Response<createcallback>) {
                if (response.isSuccessful){
                    response.body()?.let {
                      //  var manager = prefrence(this@LoginActivity)
                       // manager.getdata()
                      //  var emaill = manager.getdata()?.email
                      //  var pas = manager.getdata()?.pass

                       // if (email.equals(emaill) && pass.equals(pas)){
                          //  manager.isLogin(true)
                            startActivity(Intent(this@LoginActivity,homeActivity::class.java))
                            Toast.makeText(this@LoginActivity, "login successful", Toast.LENGTH_SHORT).show()
                       // }else{
                         //   Toast.makeText(this@LoginActivity, "login faild", Toast.LENGTH_SHORT).show()
                       // }

                    }
                }
            }

            override fun onFailure(call: Call<createcallback>, t: Throwable) {

            }


        })

    }

    private fun seterror(editText: EditText, error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        editText.requestFocus()
    }
}