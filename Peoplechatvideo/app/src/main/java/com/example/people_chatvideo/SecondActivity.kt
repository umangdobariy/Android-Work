package com.example.people_chatvideo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var tvAge: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvAge = findViewById(R.id.tvAge)
        button = findViewById(R.id.btnSubmit)


        var name = intent.getStringExtra("NAME")
        var email = intent.getStringExtra("EMAIL")
        var age = intent.getStringExtra("AGE")
//        println("name : $name  email : $email  age : $age")

        tvName.text = "Name : $name"
        tvEmail.text = "Email : $email"
        tvAge.text = "AGE: $age"


        button.setOnClickListener {

            var name = tvName.text.toString().trim()
            var email = tvEmail.text.toString().trim()
            var age = tvAge.text.toString().trim()

            var intent = Intent(this, ThirdAcitivity::class.java)
            intent.putExtra("NAME", name)
            intent.putExtra("EMAIL", email)
            intent.putExtra("AGE", age)

            startActivity(intent)

        }

    }

}