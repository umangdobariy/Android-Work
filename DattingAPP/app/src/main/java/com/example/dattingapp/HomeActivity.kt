package com.example.dattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    lateinit var kdname:TextView
    lateinit var kdemail:TextView
    lateinit var kdcontact:TextView
    lateinit var kdpassword:TextView
    lateinit var kdcpassword:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        kdname = findViewById(R.id.kdname)
        kdemail = findViewById(R.id.kdemail)
        kdcontact = findViewById(R.id.kdcontact)
        kdpassword = findViewById(R.id.kdpassword)
        kdcpassword = findViewById(R.id.kdcpassword)

        var name = intent.getStringExtra("NAME")
        var email = intent.getStringExtra("EMAIL")
        var contact = intent.getStringExtra("CONTACT")
        var password = intent.getStringExtra("PASSWORD")
        var cpassword = intent.getStringExtra("CPASSWORD")


        kdname.text = "name : $name"
        kdemail.text = "email: $email"
        kdcontact.text = "contact : $contact"
        kdpassword.text = "password : $password"
        kdcpassword.text = "cpassword $cpassword"
    }
}