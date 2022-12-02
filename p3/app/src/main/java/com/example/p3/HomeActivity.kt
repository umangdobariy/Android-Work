package com.example.p3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.p3.databinding.ActivityHomeBinding
import kotlin.math.log

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = intent.getStringExtra("NAME")
        var contact = intent.getStringExtra("CONTACT")
        var email = intent.getStringExtra("EMAIL")
        var password = intent.getStringExtra("PASSWORD")
        var cpassword = intent.getStringExtra("CPASSWORD")

        //login
        var loginEmail = intent.getStringExtra("email")
        var loginpassword = intent.getStringExtra("password")

         if(name!=null && contact != null && email != null && password!=null && cpassword!=null){
             binding.kdName.text = "name : $name"
             binding.kdContact.text = "contact : $contact"
             binding.kdEmail.text = "email  : $email"
             binding.kdPassword.text = "password  : $password"
             binding.kdCpassword.text = "cpassword  : $cpassword"
         }
        else{
             if(name==null && contact==null &&cpassword==null){
                 binding.kdName.visibility = View.GONE
                 binding.kdContact.visibility = View.GONE
                 binding.kdCpassword.visibility = View.GONE
             }
                 binding.kdEmail.text = loginEmail
                 binding.kdPassword.text = loginpassword
         }
    }
}