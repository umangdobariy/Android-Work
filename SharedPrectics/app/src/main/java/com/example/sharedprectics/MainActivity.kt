package com.example.sharedprectics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedprectics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            var name = binding.uvName.text.toString().trim()
            var email = binding.uvEmail.text.toString().trim()
            var age = if(binding.uvAge.text.toString().trim()
                .isEmpty()) 0
            else binding.uvAge.text.toString().trim().toInt()

            saveRecord(name,email,age)
        }

        binding.btnRead.setOnClickListener {

            readRecord()
        }
    }

    private fun readRecord() {

        var preferences = getSharedPreferences("khodal", MODE_PRIVATE)
        var name = preferences.getString("NAME","")
        var email = preferences.getString("EMAIL","")
        var age = preferences.getInt("AGE",0)

        binding.uvResult.text = """
            name: $name
            email : $email
            age : $age
        """.trimIndent()
    }

    private fun saveRecord(name: String, email: String, age: Int) {

        var preferences = getSharedPreferences("Khodal", MODE_PRIVATE)
        var editor = preferences.edit()
        editor.putString("NAME",name)
        editor.putString("EMAIL",email)
        editor.putInt("AGE",age)
        editor.commit()

        Toast.makeText(applicationContext, "Record save", Toast.LENGTH_SHORT).show()
        

    }
}