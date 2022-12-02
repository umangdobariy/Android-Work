package com.example.parcebleuserclassle_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var tvName:TextView
    lateinit var tvEmail:TextView
    lateinit var tvAge:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvName = findViewById(R.id.tv_name)
        tvEmail = findViewById(R.id.tv_email)
        tvAge  = findViewById(R.id.tv_age)

        var user:User = intent.getParcelableExtra<User>("USER")!!

        tvName.text = "Name : ${user.name}"
        tvEmail.text = "Email : ${user.email}"
        tvAge.text = "Age :${user.age}"

    }
}