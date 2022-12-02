package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.demo.databinding.ActivityLoginBinding
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

        binding.udLogin.setOnClickListener {


            var email = binding.udEmail.text.toString().trim()
            var password = binding.udPassword.text.toString().trim()

            requestFoucs()

            if (!isValidemail(email))
            {
                setError(binding.udEmail,"Enter your email")
            }else if (!isValidpassword(password))
            {
                setError(binding.udPassword,"Enter your password")
            }else{
                Toast.makeText(this, "All fields are validated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,HomeActivity::class.java))
            }
        }


    }

    private fun requestFoucs() {
        binding.udEmail.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udPassword.setBackgroundResource(R.drawable.rd_backgroundshape)

        binding.udEmail.requestFocus()
        binding.udPassword.requestFocus()

    }

    private fun setError(editText: EditText?,message: String){
        editText?.let {
            it.background = ContextCompat.getDrawable(this,R.drawable.ic_ud_error)
            it.requestFocus()
        }
    }

    private fun isValidemail(email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidpassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }



}