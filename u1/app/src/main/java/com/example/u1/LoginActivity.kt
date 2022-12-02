package com.example.u1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.u1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding

    val rdSignup:Button
    get() = findViewById(R.id.rd_signup)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rdSignup.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))

        }
    }


}