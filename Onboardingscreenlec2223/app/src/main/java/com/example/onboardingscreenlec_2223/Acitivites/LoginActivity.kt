package com.example.onboardingscreenlec_2223.Acitivites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.mahadev.shredpref.PrefManagr
import com.example.onboardingscreenlec_2223.HomeActivity
import com.example.onboardingscreenlec_2223.R
import com.example.onboardingscreenlec_2223.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

        val rdSignup: Button
        get() = findViewById(R.id.rd_signup)

        val btnForgot:Button
        get() = findViewById(R.id.rd_forgot)

       val btnLogin:Button
       get() = findViewById(R.id.rd_login)




            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding =ActivityLoginBinding.inflate(layoutInflater)
                setContentView(binding.root)

                binding.rdSignup.setOnClickListener {
                    startActivity(Intent(this, SignupActivity::class.java))
                }
                binding.rdLogin.setOnClickListener {
                    var email = binding.rdEmail.text.toString().trim()
                    var password = binding.rdPassword.text.toString().trim()

                    var prefManagr = PrefManagr(this)
                    prefManagr.setLoginStatus(true)


                    var  intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)



                    resetFocus()

                    if (!isValidemail(email)) {
                        setError(binding.rdEmail, "Enter your email")
                    } else if (!isValidPassword(password)) {
                        setError(binding.rdPassword, "Enter valid password")
                    } else {
                        Toast.makeText(this, "All fields are validated", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, SignupActivity::class.java))
                    }
                }
            }


    private fun resetFocus() {
        binding.rdEmail.background = ContextCompat.getDrawable(this,R.drawable.rd_backgroundshape)
        binding.rdPassword.background = ContextCompat.getDrawable(this,R.drawable.rd_backgroundshape)

        binding.rdEmail.clearFocus()
        binding.rdPassword.clearFocus()
    }

    private fun setError(editText: EditText?, message: String) {
        editText?.let {
            it.background = ContextCompat.getDrawable(this,R.drawable.et_error)
            it.requestFocus()
        }
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()

    }

    private fun isValidemail(email:String):Boolean
    {
        return  Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
}

