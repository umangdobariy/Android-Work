package app.project3.postdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import app.project3.CallBack.RegisterCallBackResponse
import app.project3.databinding.ActivityLoginBinding
import app.project3.network.ApiClient
import app.project3.pref.prefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {}

        binding.btnRead.setOnClickListener {}

        binding.udSignin.setOnClickListener {

            var email = binding.udEmail.text.toString().trim()
            var password = binding.udPassword.text.toString().trim()

            if (email.isEmpty()) {
                setError(binding.udEmail, "enter email")
            } else if (password.isEmpty()) {
                setError(binding.udPassword, "enter password")
            } else {
                UserLogin(email, password)
            }

        }


        binding.udSignUp.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


    }

    private fun UserLogin(email: String, password: String) {

        ApiClient.init().LoginUser(6, email, password)
            .enqueue(object : Callback<RegisterCallBackResponse> {
                override fun onResponse(
                    call: Call<RegisterCallBackResponse>,
                    response: Response<RegisterCallBackResponse>
                ) {

                    if(response.isSuccessful){
                        response.body().let {
                            var manager=prefManager(this@LoginActivity)
                            manager.isLoginStatus(true)
                            startActivity(Intent(this@LoginActivity,HomeAcitvity::class.java))
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterCallBackResponse>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}", )
                }

            })

    }
    private fun setError(editText: EditText, error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        editText.requestFocus()
    }

}