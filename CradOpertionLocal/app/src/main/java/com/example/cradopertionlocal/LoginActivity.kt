package com.example.cradopertionlocal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cradopertionlocal.databinding.ActivityLoginBinding
import com.example.cradopertionlocal.databinding.ActivityMainBinding
import java.util.prefs.Preferences

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    //var strEmail = ""
    // var strPass: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //   strEmail = intent.getStringExtra("Email").toString()
        //   strPass = intent.getIntExtra("pass", 0)

        binding.btnSave.setOnClickListener {

            var email = binding.udEmail.text.toString().trim()
            var password = if (binding.udPassword.text.toString().isEmpty()) {
                0
            } else {
                binding.udPassword.text.toString().trim().toInt()
            }

            saveRecord(email, password)

        }


        binding.btnRead.setOnClickListener {

            var preferences: SharedPreferences = getSharedPreferences("Bajrang", MODE_PRIVATE)
            var email = preferences.getString("EMAIL", "").toString()
            var password = preferences.getInt("PASSWORD", 0)

            binding.udResult.text = """
                email:$email
                password :$password
            """.trimIndent()
        }

        binding.udSignin.setOnClickListener {

            //  var preferences: SharedPreferences = getSharedPreferences("Bajrang", MODE_PRIVATE)
            //  var email = preferences.getString("EMAIL", "").toString()
            // var password = preferences.getInt("PASSWORD",0)

            // var prefences: SharedPreferences = getSharedPreferences("Bajrang", MODE_PRIVATE)
            //  var editor: SharedPreferences.Editor = prefences.edit()
            //  editor.putString("EMAIL", strEmail).toString()
            //  editor.putInt("PASSWORD", strPass).toString().trim()
            //   editor.commit()

            //   if(preferences.contains("Email")){
            //   strEmail= intent.getStringExtra("Email").toString()
            //   }
            //   else if(preferences.contains("pass")){
            //   strPass=intent.getIntExtra("pass",0)
            //    }


            var emaildata = binding.udEmail.text.toString()
            var passdata = binding.udPassword.text.toString()


            var strEmail = intent.getStringExtra("Email").toString()
            var strPass = intent.getIntExtra("pass", 0)


            if (binding.udEmail.text.isEmpty()) {
                binding.udEmail.error = "enter email"
                //binding.udPassword.error = "enter password"

            } else if (binding.udPassword.text.isEmpty()) {
                binding.udPassword.error = "enter password"
            } else {

                if (strEmail == emaildata) {
                    Toast.makeText(this, "Error Pass", Toast.LENGTH_SHORT).show()
                } else {
                    var intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }

                //var intent = Intent(this, HomeActivity::class.java)
                //intent.putExtra("Email",email)
                //intent.putExtra("pass",password)
                //startActivity(intent)
            }

        }

        binding.udSignUp.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            // intent.putExtra("Email",email)
            // intent.putExtra("pass",password)
            startActivity(intent)
        }
    }


    private fun saveRecord(email: String, password: Int) {

        var prefences: SharedPreferences = getSharedPreferences("Bajrang", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = prefences.edit()
        editor.putString("EMAIL", email).toString()
        editor.putInt("PASSWORD", password).toString().trim()
        editor.commit()


        Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_SHORT).show()

    }
}