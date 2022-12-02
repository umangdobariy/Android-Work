package com.example.amazon_new.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amazon_new.R
import com.example.amazon_new.databinding.ActivitySplashScreenBinding

class Splash_Screen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread(Runnable {
            try {
                Thread.sleep(1500)
                var intent = Intent(applicationContext,First_Page::class.java)
                startActivity(intent)

            }catch (e:Exception){

            }

        }).start()
    }
}