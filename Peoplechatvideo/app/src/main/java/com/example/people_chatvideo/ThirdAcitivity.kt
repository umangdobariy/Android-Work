package com.example.people_chatvideo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdAcitivity:AppCompatActivity() {
    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var tvAge: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)


        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvAge = findViewById(R.id.tvAge)


        var name = intent.getStringExtra("NAME")
        var email = intent.getStringExtra("EMAIL")
        var age = intent.getStringExtra("AGE")
//        println("name : $name  email : $email  age : $age")

        tvName.text = name
        tvEmail.text = email
        tvAge.text = age


    }
}