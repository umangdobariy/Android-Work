package com.example.navigationdrawerlec_52.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigationdrawerlec_52.R
import com.example.navigationdrawerlec_52.sharepref.SharePref

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread(
            Runnable {

                var prefManagr = SharePref(this)

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
                        startActivity(Intent(this,MainActivity::class.java))
                        startActivity(intent)
                    }
                }
            }
        ).start()
    }
}