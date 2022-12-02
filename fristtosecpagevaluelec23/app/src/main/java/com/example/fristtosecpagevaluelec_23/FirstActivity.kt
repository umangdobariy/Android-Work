package com.example.fristtosecpagevaluelec_23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FirstActivity : AppCompatActivity() {

    lateinit var btnSubmit: Button
    lateinit var etName:EditText
    lateinit var etEmail:EditText
    lateinit var etAge: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        btnSubmit = findViewById(R.id.btn_submit)
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etAge = findViewById(R.id.et_age)

        // Button Click event
        btnSubmit.setOnClickListener {
            // this block will be execute if user click the submit button

            var name = etName.text.toString().trim()
            var email = etEmail.text.toString().trim()
            var age = etAge.text.toString().trim()

            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NAME", name)
            intent.putExtra("EMAIL", email)
            intent.putExtra("AGE", age)
            startActivity(intent)

        }
    }
}