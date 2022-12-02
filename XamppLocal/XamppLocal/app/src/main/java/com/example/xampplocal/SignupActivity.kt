package com.example.xampplocal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitnetwork.database.ApiClient
import com.example.xampplocal.callback.RegisterCallbackResponse
import com.example.xampplocal.databinding.ActivitySignupBinding
import com.example.xampplocal.sharepref.PrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

        }

        binding.btnSignup.setOnClickListener {

            var regex = ("^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$")
            val name = binding.etSname.text.toString().trim()
            val email = binding.etSemail.text.toString().trim()
            val number = binding.etContact.text.toString().trim()
            val password = binding.etPass.text.toString().trim()

            if(name.isEmpty()){
                binding.etSname.error = "Enter valid first name"
            }else if(email.isEmpty()){
                binding.etSemail.error ="Enter valid last name"
            }else if(number.isEmpty()){
               binding.etContact.error =  "Enter valid email"
            } else if(password.isEmpty() && password.matches(regex.toRegex())){
                binding.etContact.error =  "Enter valid password"
            }else {
                createAccount(name,email,number,password)
            }
        }
    }

    private fun createAccount(name: String, email: String, number: String,pass:String) {

        ApiClient.init().createAccount(1,name,email,number,pass)
            .enqueue(object: Callback<RegisterCallbackResponse>{
            override fun onResponse(
                call: Call<RegisterCallbackResponse>,
                response: Response<RegisterCallbackResponse>
            ) {
               if(response.isSuccessful){
                   response.body()?.let {
                       Toast.makeText(applicationContext, it.msg, Toast.LENGTH_SHORT).show()
                       if (it.status == "success") {
                           // save user object to sharedpreference
                           // navigate to home page
                           var manager = PrefManager(applicationContext)
                           manager.setLoginStatus(true)
                           manager.setUser(it.user)

                           startActivity(Intent(applicationContext, HomeActivity::class.java))
                           finish()

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