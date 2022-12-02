package com.example.mahadev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread(
            Runnable {
                Thread.sleep(3000)
                startActivity(Intent(this,LoginActivity::class.java))
            }
        ).start()
    }
}