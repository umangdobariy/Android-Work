package com.example.people_chatvideo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class FristActivity : AppCompatActivity() {

    lateinit var btnsubmit:Button
    lateinit var etName:EditText
    lateinit var etEmail:EditText
    lateinit var etAge:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frist)

        btnsubmit = findViewById(R.id.btn_submit)
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etAge = findViewById(R.id.et_age)

        btnsubmit.setOnClickListener {

            var name = etName.text.toString().trim()
            var email = etEmail.text.toString().trim()
            var age = etAge.text.toString().trim()

//            println("name : $name  email : $email  age : $age")
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NAME", name)
            intent.putExtra("EMAIL", email)
            intent.putExtra("AGE", age)

            startActivity(intent)
        }
    }
}