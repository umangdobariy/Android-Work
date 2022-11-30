package com.example.storgeoptionsharedperferncelec_25

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.storgeoptionsharedperferncelec_25.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            var name = binding.udName.text.toString().trim()
            var email = binding.udEmail.text.toString().trim()
            var age =if (binding.udAge.text.toString().trim()
                    .isEmpty()) 0
            else binding.udAge.text.toString().trim().toInt()

            saveRecord(name,email,age)
        }

        binding.btnRead.setOnClickListener {

            readRecord()
        }
    }

    private fun readRecord() {

        var preference:SharedPreferences = getSharedPreferences("Mahadev", MODE_PRIVATE)
        var name = preference.getString("NAME","")  // 2nd parameter = default value
        var email = preference.getString("EMAIL","")
        var age = preference.getInt("AGE",0)

        binding.udResult.text = """
            name: $name
            email: $email
            age: $age
        """.trimIndent()

    }

    private fun saveRecord(name: String, email: String, age: Int) {
                        //type intances
        var preference:SharedPreferences = getSharedPreferences("Mahadev", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = preference.edit()
        editor.putString("NAME",name)
        editor.putString("EMAIL",email)
        editor.putInt("AGE",age)
        editor.commit()

        Toast.makeText(applicationContext, "Record saved" , Toast.LENGTH_SHORT).show()
    }
}