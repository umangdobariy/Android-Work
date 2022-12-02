package com.example.formvalidlec_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    lateinit var kdname:TextView
    lateinit var kdemail:TextView
    lateinit var kdcontact:TextView
    lateinit var kdpassword:TextView
    lateinit var kdconfirmpassword:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        kdname = findViewById(R.id.Kd_name)
        kdcontact = findViewById(R.id.Kd_contact)
        kdemail = findViewById(R.id.Kd_email)
        kdpassword = findViewById(R.id.Kd_password)
        kdconfirmpassword = findViewById(R.id.Kd_confirmpassword)

        var name = intent.getStringExtra("name")
        var email = intent.getStringExtra("email")
        var contact = intent.getStringExtra("contact")
        var password = intent.getStringExtra("password")
        var cpassword = intent.getStringExtra("cpassword")

        kdemail.text = "email : $email"
        kdpassword.text = "password: $password"
        if (name!=null)
            kdname.text = "name : $name"
        if (contact!=null)
            kdcontact.text ="contact:$contact"
        if (cpassword!=null)
            kdconfirmpassword.text = "cpassword :$cpassword"

    }
}
