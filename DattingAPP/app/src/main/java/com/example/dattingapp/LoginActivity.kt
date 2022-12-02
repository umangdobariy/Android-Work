package com.example.dattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    lateinit var btnlogin:Button
    lateinit var udemail:EditText
    lateinit var udpassword:EditText
    lateinit var btnforgotpassword:Button
    lateinit var btnsignup:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnlogin = findViewById(R.id.btn_login)
        udemail = findViewById(R.id.ud_email)
        udpassword = findViewById(R.id.ud_password)
        btnforgotpassword = findViewById(R.id.btn_forgotpassword)
        btnsignup = findViewById(R.id.btn_signup)

        btnforgotpassword.setOnClickListener {

            var intent = Intent(this,ForgotpasswordActivity::class.java)
            startActivity(intent)
        }

        btnsignup.setOnClickListener {

            var intent = Intent(this,SignupActivity::class.java)
            intent.putExtra("EMAIL",udemail.text.toString().trim())
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            var email= udemail.text.toString().trim()
            var password = udpassword.text.toString().trim()

            var intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("EMAIL",email)
            intent.putExtra("PASSWORD",password)
            startActivity(intent)

        }


    }
}