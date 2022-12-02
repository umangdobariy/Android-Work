    package com.example.formvalidlec_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.formvalidlec_5.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignupBinding

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rdRegister.setOnClickListener {
            var name = binding.rdName.text.toString().trim()
            var email = binding.rdEmail.text.toString().trim()
            var contact = binding.rdContact.text.toString().trim()
            var password = binding.rdPassword.text.toString().trim()
            var cpassword = binding.rdCpassword.text.toString().trim()

            if (isvalidEmail(email) && isvalidcontact(contact) && isvalidpassword(password) && isvalidcpassword(cpassword))
            {
                val intent=Intent(this,HomeActivity::class.java)
                intent.putExtra("name",binding.rdName.text.toString())
                intent.putExtra("email",binding.rdEmail.text.toString())
                intent.putExtra("contact",binding.rdContact.text.toString())
                intent.putExtra("password",binding.rdPassword.text.toString())
                intent.putExtra("cpassword",binding.rdCpassword.text.toString())
                startActivity(intent)
            }else
            {
                Toast.makeText(this,"error in name or contact or email or password or cpassword",Toast.LENGTH_LONG).show()
            }
            if (name.isEmpty())
            {
                binding.rdName.error = "Enter your name"
            }else if (!isvalidEmail(email))
            {
                binding.rdEmail.error = "Enter your email"
            }else if (!isvalidcontact(contact))
            {
                binding.rdContact.error = "Enter your contact"
            }else if (!isvalidpassword(password))
            {
                binding.rdPassword.error = "Enter your password"
            }else if (!isvalidcpassword(cpassword))
            {
                binding.rdCpassword.error = "Enter your cpassword"
            }else
            {
                Toast.makeText(applicationContext,"All fields are validated",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isvalidcontact(contact:String):Boolean
    {
        return contact.length==10
    }
    private fun isvalidEmail(email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isvalidpassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
    private fun isvalidcpassword(cpassword:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(cpassword).matches()
    }
}