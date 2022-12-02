package com.example.localhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.localhost.databinding.ActivityHomeAcitivityBinding
import com.example.localhost.pref.PrefManager

class HomeAcitivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var manager = PrefManager(this)

        manager.getUser()?.let {
            binding.result.text = """
                id:${it.id}
                name:${it.name}
                email:${it.email}
                contact:${it.contact}
            """.trimIndent()
        }
    }
}