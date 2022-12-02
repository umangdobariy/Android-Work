package com.example.fristtosecpagevaluelec_23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var tvName: TextView
    lateinit var tvEmail:TextView
    lateinit var tvAge:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        tvName = findViewById(R.id.tv_name)
        tvEmail = findViewById(R.id.tv_email)
        tvAge = findViewById(R.id.tv_age)

        var name =   intent.getStringExtra("NAME")
        var email =  intent.getStringExtra("EMAIL")
        var age =    intent.getStringExtra("AGE")
//        println("name : $name  email : $email  age : $age")

        tvName.text = "Name : $name"
        tvEmail.text = "Email : $email"
        tvAge.text = "Age : $age"

    }
}