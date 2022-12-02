package com.example.roompersistancedatabaselec_2627

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.roompersistancedatabaselec_2627.database.Appdatabase
import com.example.roompersistancedatabaselec_2627.database.entity.User
import com.example.roompersistancedatabaselec_2627.databinding.ActivityUserBinding
import java.lang.Exception

class UserActivity : AppCompatActivity() {


    lateinit var db: Appdatabase

    private lateinit var binding: ActivityUserBinding
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            this, Appdatabase::class.java, "tops_student"
        ).allowMainThreadQueries().build()

        user = intent.getParcelableExtra("USER")

        if (user != null) {
            binding.udAdd.text = "Update"

            user?.let {
                binding.udName.setText(it.name)
                binding.udEmail.setText(it.email)
                binding.udContact.setText(it.contact)
            }
        }
        binding.udAdd.setOnClickListener {
            var name = binding.udName.text.toString().trim()
            var email = binding.udEmail.text.toString().trim()
            var contact = binding.udContact.text.toString().trim()

            if(binding.udAdd.text=="Update"){
                updateRecord(name, email, contact)
            }else{
                addRecord(name, email, contact)
            }

        }

    }

    private fun updateRecord(name: String, email: String, contact: String) {

        var muser = User(id =user!!.id,  email = email, name = name, contact = contact)
        try {
            db.userDao.updateRecord(muser)

            Toast.makeText(applicationContext,"Record added successfully",Toast.LENGTH_SHORT).show()

            binding.udName.setText("")
            binding.udEmail.setText("")
            binding.udContact.setText("")

            onBackPressed()

        }catch (e:Exception){

        }

    }

    private fun addRecord(name: String, email: String, contact: String) {

        var user = User(email = email, name = name, contact = contact)
        try {
            db.userDao.insertRecord(user)

            Toast.makeText(applicationContext, "Record added successfully", Toast.LENGTH_SHORT)
                .show()

            binding.udName.setText("")
            binding.udEmail.setText("")
            binding.udContact.setText("")

            onBackPressed()

        } catch (e: Exception) {

        }
    }
}