package app.Api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import app.Api.callback.RegisterCallbackResponse
import app.Api.databinding.ActivityLoginBinding
import app.Api.network.ApiClient
import app.Api.pref.prefManager
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {  }

        binding.btnRead.setOnClickListener {  }


        binding.udSignin.setOnClickListener {

            var email = binding.udEmail.text.toString().trim()
            var password = binding.udPassword.text.toString().trim()

            if (email.isEmpty()){
                setError(binding.udEmail,"Enter email")
            }else if (password.isEmpty()){
                setError(binding.udPassword,"Enter password")
            } else {
                UserLogin(email,password)
            }
        }

    }

    private fun UserLogin(email:String,password:String){

        ApiClient.init().LoginUser(6,email,password)
            .enqueue(object : retrofit2.Callback<RegisterCallbackResponse> {
                override fun onResponse(
                    call: Call<RegisterCallbackResponse>,
                    response: Response<RegisterCallbackResponse>
                ) {

                    if (response.isSuccessful){
                        response.body().let {
                            var manager = prefManager(this@LoginActivity)
                            manager.isLoginStatus(true)
                            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterCallbackResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message} ")
                }

            })

    }




    private fun setError(editText: EditText, error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        editText.requestFocus()
    }

}