package com.example.p3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.p3.databinding.ActivitySecondBinding
import java.util.regex.Pattern

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=  .*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rdRegister.setOnClickListener {

            var name = binding.rdName.text.toString().trim()
            var contact = binding.rdContact.text.toString().trim()
            var email = binding.rdEmail.text.toString().trim()
            var password = binding.rdPassword.text.toString().trim()
            var cpassword = binding.rdCpassword.text.toString().trim()


            if (name.isEmpty())
            {
                binding.rdName.error = "Enter your name"
                Toast.makeText(applicationContext,"error name",Toast.LENGTH_SHORT).show()
            }else if (!isvalidContact(contact))
            {
                binding.rdContact.error = "Enter your contact"
                Toast.makeText(applicationContext,"error contact",Toast.LENGTH_SHORT).show()
            }else if (!isvalidemail(email))
            {
                binding.rdEmail.error = "Enter your email"
                Toast.makeText(applicationContext,"error email",Toast.LENGTH_SHORT).show()
            }else if (!isvalidpassword(password))
            {
                binding.rdPassword.error = "Enter your password"
                Toast.makeText(applicationContext,"error password",Toast.LENGTH_SHORT).show()
            }else if(!isvalidcpassword(cpassword))
            {
                binding.rdCpassword.error = "Enter your cpassword"
            }
            else{
                Toast.makeText(applicationContext,"all fields are valid",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("NAME",name)
                intent.putExtra("CONTACT",contact)
                intent.putExtra("EMAIL",email)
                intent.putExtra("PASSWORD",password)
                intent.putExtra("CPASSWORD",cpassword)
                startActivity(intent)
            }
        }
    }

    private fun isvalidContact(contact:String):Boolean
    {
        return contact.length==10
    }
    private fun isvalidemail(email:String):Boolean
    {
        return  Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private  fun isvalidpassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
    private fun isvalidcpassword(cpassword:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(cpassword).matches()
    }
}