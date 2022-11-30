package com.example.onboardingscreenlec_2223

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mahadev.shredpref.PrefManagr
import com.example.onboardingscreenlec_2223.Acitivites.LoginActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btn_logout).setOnClickListener {

            var prefManagr = PrefManagr(this)
            prefManagr.setLoginStatus(false)

            startActivity(Intent(this,LoginActivity::class.java))
            finishAffinity()
        }
    }
}