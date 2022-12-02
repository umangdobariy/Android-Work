package com.example.p3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.p3.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.udSignup.setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
        }

        binding.udLogin.setOnClickListener {

            var email = binding.udEmail.text.toString().trim()
            var password = binding.udPassword.text.toString().trim()

            if (email.isEmpty())
            {
                binding.udEmail.error = "Enter your email"
                Toast.makeText(applicationContext,"error email",Toast.LENGTH_SHORT).show()
            }else if (!isvalidpassword(password))
            {
                binding.udPassword.error = "Enter your password"
                Toast.makeText(applicationContext,"error password",Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(applicationContext,"all fields are validated",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("email",email)
                intent.putExtra("password",password)
                startActivity(intent)
            }
        }

    }

    private fun isvalidemail(email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isvalidpassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
}