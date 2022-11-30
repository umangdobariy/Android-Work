package com.example.onboardingscreenlec_2223.Acitivites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mahadev.shredpref.PrefManagr
import com.example.onboardingscreenlec_2223.HomeActivity
import com.example.onboardingscreenlec_2223.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread(
            Runnable {

                var prefManagr = PrefManagr(this)

                if (!prefManagr.getIntroStatus()){

                    Thread.sleep(3000)
                    startActivity(Intent(this,IntroActivity::class.java))
                    startActivity(intent)
                }else{

                    if (!prefManagr.getLoginStatus())
                    {
                        startActivity(Intent(this,LoginActivity::class.java))
                        startActivity(intent)
                    }else{
                        startActivity(Intent(this,HomeActivity::class.java))
                        startActivity(intent)
                    }
                }


            }
        ).start()
    }
}