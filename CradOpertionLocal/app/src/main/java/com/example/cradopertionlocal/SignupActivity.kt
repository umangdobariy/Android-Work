package com.example.cradopertionlocal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cradopertionlocal.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    var stremail=""
    var strPass=""
    var email:String=""
    var password:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rdSave.setOnClickListener {
            var name = binding.rdName.text.toString().trim()
            var email = binding.rdEmail.text.toString().trim()
            var contact = binding.rdContact.text.toString().trim().toInt()
            var password = binding.rdPassword.text.toString().trim().toInt()
            var cpassowrd = binding.rdCpassword.text.toString().trim().toInt()

            saveRecord(name,email,contact,password,cpassowrd)
        }

        binding.rdRead.setOnClickListener {

            var preferences:SharedPreferences = getSharedPreferences("Mahadev", MODE_PRIVATE)
            var name = preferences.getString("NAME","")
            var email = preferences.getString("EMAIL","").toString()
            var contact = preferences.getInt("CONTACT",0)
            var password = preferences.getInt("PASSWORD",0)
            var cpassword = preferences.getInt("CPASSWORD",0)

            binding.rdResult.text = """
                name:$name
                email:$email
                contact:$contact
                password:$password
                cpassword:$cpassword
            """.trimIndent()



        }


        binding.btnSignUp.setOnClickListener {

            var preferences:SharedPreferences = getSharedPreferences("Mahadev", MODE_PRIVATE)
            //var name = preferences.getString("NAME","")
            var email = preferences.getString("EMAIL","").toString()
            //var contact = preferences.getInt("CONTACT",0)
            var password = preferences.getInt("PASSWORD",0)

            var intent= Intent(this,LoginActivity::class.java)
            intent.putExtra("Email",email)
            intent.putExtra("pass",password)
            startActivity(intent)

        }

    }

    private fun saveRecord(name: String, email: String, contact: Int, password: Int, cpassowrd: Int) {

        var preferences:SharedPreferences = getSharedPreferences("Mahadev", MODE_PRIVATE)
        var editor:SharedPreferences.Editor = preferences.edit()
        editor.putString("NAME",name)
        editor.putString("EMAIL",email)
        editor.putInt("CONTACT",contact)
        editor.putInt("PASSWORD",password)
        editor.putInt("CPASSWORD",cpassowrd)
        editor.commit()

        Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_SHORT).show()


    }
}