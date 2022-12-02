package com.example.dattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignupActivity : AppCompatActivity() {

    lateinit var rdsignup:Button
    lateinit var rdname:EditText
    lateinit var rdemail:EditText
    lateinit var rdcontact:EditText
    lateinit var rdpassword:EditText
    lateinit var rdconfirmpassword:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        rdname = findViewById(R.id.rd_name)
        rdemail = findViewById(R.id.rd_email)
        rdcontact = findViewById(R.id.rd_contact)
        rdpassword = findViewById(R.id.rd_password)
        rdconfirmpassword = findViewById(R.id.rd_confirmpassword)
        rdsignup = findViewById(R.id.rd_signup)

    rdsignup.setOnClickListener {

        var name = rdname.text.toString().trim()
        var email = rdemail.text.toString().trim()
        var contact = rdcontact.text.toString().trim()
        var password = rdpassword.text.toString().trim()
        var confirmpassword = rdconfirmpassword.text.toString().trim()

        var intent = Intent(this,HomeActivity::class.java)
        intent.putExtra("NAME",name)
        intent.putExtra("EMAIL",email)
        intent.putExtra("CONTACT",contact)
        intent.putExtra("PASSWORD",password)
        intent.putExtra("CPASSWORD",confirmpassword)
        startActivity(intent)
    }

    }
}