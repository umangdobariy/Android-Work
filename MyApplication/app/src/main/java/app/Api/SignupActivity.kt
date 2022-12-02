package app.Api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.EditText
import android.widget.Toast
import app.Api.callback.RegisterCallbackResponse
import app.Api.databinding.ActivitySignupBinding
import app.Api.model.User
import app.Api.network.ApiClient
import app.Api.pref.prefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            } else if (email.isEmpty()) {
                setError(binding.etEmail, "enter email")
            } else if (contect.isEmpty()) {
                setError(binding.etContect, "enter contact")
            } else if (password.isEmpty()) {
                setError(binding.etPassword, "enter password")
            } else {
                CreateAccount(name, email, contect, password)
            }
        }
    }

    private fun setError(editText: EditText, error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        editText.requestFocus()
    }


     fun CreateAccount(name: String, email: String, contact: String, password: String) {


        ApiClient.init().CreateAccount(1, name, email, contact, password)
            .enqueue(object : Callback<RegisterCallbackResponse> {
                override fun onResponse(
                    call: Call<RegisterCallbackResponse>,
                    response: Response<RegisterCallbackResponse>
                ) {

                    var res = response.body()

                    res?.let {
                        if (it.status == "success") {
                            Toast.makeText(this@SignupActivity, "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                            var manager = prefManager(this@SignupActivity)
                            manager.isLoginStatus(true)
                            manager.setUser(it.user)
                            startActivity(Intent(this@SignupActivity, HomeActivity::class.java))
                        }
                    }
                }
                override fun onFailure(call: Call<RegisterCallbackResponse>, t: Throwable) {
                    Toast.makeText(this@SignupActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
    }

}
