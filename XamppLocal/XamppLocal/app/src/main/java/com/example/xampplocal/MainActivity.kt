package com.example.xampplocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xampplocal.databinding.ActivityMainBinding
import com.example.xampplocal.sharepref.PrefManager

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        var manager = PrefManager(this)

        manager.getUser()?.let {
            binding.result.text = """
                id : ${it.id}
                name : ${it.name}
                email : ${it.email}
                contact : ${it.contact}
                
            """.trimIndent()
        }*/
    }
}