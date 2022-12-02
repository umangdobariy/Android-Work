package com.example.parcebleuserclassle_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FristActivity : AppCompatActivity() {

    lateinit var btnSubmit :Button
    lateinit var etName:EditText
    lateinit var etEmail:EditText
    lateinit var etAge:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frist)

        btnSubmit = findViewById(R.id.btn_submit)
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etAge = findViewById(R.id.et_age)

        btnSubmit.setOnClickListener {

            var name = etName.text.toString().trim()
            var email = etEmail.text.toString().trim()
            var age = etAge.text.toString().trim()

            var user = User(name,email,age.toInt())

            var intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("USER",user)
            startActivity(intent)

        }

    }
}