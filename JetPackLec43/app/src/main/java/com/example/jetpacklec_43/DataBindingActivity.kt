package com.example.jetpacklec_43

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.jetpacklec_43.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    val tvName:TextView
    get() = findViewById(R.id.tv_name)

    val tvEmail:TextView
    get() = findViewById(R.id.tv_email)

    private lateinit var binding:ActivityDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)

        binding.person = Person("Umang Dobariya","Umang@gmail.com")

//        tvName.text = "Umang Patel"
//        tvEmail.text = "umang@gmail.com"


        binding.btnUpdate.setOnClickListener {

            binding.person = Person("Karnika Bharodiya","Karnika@gmail.com")
//            tvName.text = person.name
//            tvEmail.text = person.email
        }
    }
}