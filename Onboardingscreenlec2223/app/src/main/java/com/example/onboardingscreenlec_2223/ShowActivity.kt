package com.example.onboardingscreenlec_2223

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingscreenlec_2223.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()

    }

    private fun initview() {
        let {

        }
    }
}