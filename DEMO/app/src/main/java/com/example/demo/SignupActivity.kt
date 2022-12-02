package com.example.demo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.demo.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resetFocus()

        binding.udRegsiter.setOnClickListener {
            var name = binding.udName.text.toString().trim()
            var contact = binding.udContact.text.toString().trim()
            var email = binding.udEmail.text.toString().trim()
            var password = binding.udPassword.text.toString().trim()
            var cpassword = binding.udCpassword.text.toString().trim()

            resetFocus()

            if (name.isEmpty()){
                setError(binding.udName,"Enter your name")
            }else if (!isValidContact(contact))
            {
                setError(binding.udEmail,"Enter the contact")
            }else if (!isValidEmail(email))
            {
                setError(binding.udEmail,"Enter the email")
            }else if (!isValidPassword(password))
            {
                setError(binding.udPassword,"Enter the password")
            }else if (!isValidCpassword(cpassword))
            {
                setError(binding.udCpassword,"Enter the cpassword")
            }else{

                startActivity(Intent(this,HomeActivity::class.java))
                Toast.makeText(this, "All fields are validated", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun resetFocus() {
        binding.udName.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udContact.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udEmail.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udPassword.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udCpassword.setBackgroundResource(R.drawable.rd_backgroundshape)

        binding.udName.clearFocus()
        binding.udContact.clearFocus()
        binding.udEmail.clearFocus()
        binding.udPassword.clearFocus()
        binding.udCpassword.clearFocus()

    }

    private fun setError(editText: EditText?,message:String){
        editText?.let {
            it.background = ContextCompat.getDrawable(this,R.drawable.ic_ud_error)
            it.requestFocus()
        }
    }

    private fun isValidContact(context:String):Boolean
    {
        return context.length==10
    }
    private fun isValidEmail(email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
    private fun isValidCpassword(cpassword:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(cpassword).matches()
    }
}