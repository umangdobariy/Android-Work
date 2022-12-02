package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.database.Appdatabase
import com.example.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db:Appdatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        

        binding.ukSave.setOnClickListener {

            var name = binding.ukName.text.toString().trim()
            var email = binding.ukEmail.text.toString().trim()

            insertRecord(name,email)
        }
    }

    private fun insertRecord(name: String, email: String) {


    }
}