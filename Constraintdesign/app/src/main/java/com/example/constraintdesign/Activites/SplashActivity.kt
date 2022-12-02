package com.example.constraintdesign.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.constraintdesign.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread(
            Runnable {
                Thread.sleep(3000)
                startActivity(Intent(this,AuthActivity::class.java))
            }
        ).start()
    }
}