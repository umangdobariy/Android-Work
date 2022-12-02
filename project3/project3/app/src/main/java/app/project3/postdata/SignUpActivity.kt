package app.project3.postdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import app.project3.CallBack.RegisterCallBackResponse
import app.project3.databinding.ActivitySignupBinding
import app.project3.network.ApiClient
import app.project3.pref.prefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    //var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+"
    //var passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$"

    override fun onResume() {
        super.onResume()

       // if (prefManager(this).getLoginStatus()) {
       //     startActivity(Intent(this, HomeAcitvity::class.java))
       // }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_set_data)
        init()
    }

    fun init() {


        binding.btSubmit.setOnClickListener {

            var name = binding.etName.text.toString().trim()
            var email = binding.etEmail.text.toString().trim()
            var contect = binding.etContect.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()

            if (name.isEmpty()) {
                setError(binding.etName, "enter name")
            }
            else if(email.isEmpty()){
                setError(binding.etEmail, "enter Email")
            }
            else if(contect.isEmpty()){
                setError(binding.etContect, "enter contect no")
            }
            else if (password.isEmpty()) {
                setError(binding.etPassword, "enter Password")
            }
            else{
                CreateAccount(name, email, contect, password)
            }
        }

        binding.btLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

    private fun setError(editText: EditText, error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        editText.requestFocus()
    }

    fun CreateAccount(name: String, email: String, contect: String, password: String) {

        ApiClient.init().CreateAccount(1, name, email, contect, password)
            .enqueue(object : Callback<RegisterCallBackResponse> {
                override fun onResponse(
                    call: Call<RegisterCallBackResponse>,
                    response: Response<RegisterCallBackResponse>
                ) {

                    var res = response.body()

                    res?.let {

                        if (it.status == "success") {

                            //save user shared pref
                            //navigate to home page

                            var manager = prefManager(applicationContext)
                            manager.isLoginStatus(true)
                            manager.setUser(it.user)

                            startActivity(Intent(this@SignUpActivity, HomeAcitvity::class.java))
                        }

                        Toast.makeText(this@SignUpActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterCallBackResponse>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, t.toString(), Toast.LENGTH_SHORT).show()

                }

            })

    }

}