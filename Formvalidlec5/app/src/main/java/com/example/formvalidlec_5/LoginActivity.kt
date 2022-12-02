package com.example.formvalidlec_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.example.formvalidlec_5.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    val udSignup:Button
    get() = findViewById(R.id.ud_signup)

    val udLogin:Button
    get() = findViewById(R.id.ud_login)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.udSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }


        binding.udLogin.setOnClickListener {
            var email = binding.udEmail.text.toString().trim()
            var password = binding.udPassword.text.toString().trim()

            if (isvalidemail(email) && isvalidpassword(password)) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", binding.udEmail.text.toString())
                intent.putExtra("password", binding.udPassword.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "error in email or password", Toast.LENGTH_LONG).show()
            }

            if (!isvalidemail(email)) {
                binding.udEmail.error = "Enter your email"
            } else if (!isvalidpassword(password)) {
                binding.udPassword.error = "Enter valid password"
            }
        }
    }
    private fun isvalidemail(email: String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isvalidpassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
}