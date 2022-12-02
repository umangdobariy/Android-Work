package com.example.xampplocal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.xampplocal.sharepref.PrefManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            var prefManager = PrefManager(this)
            if(!prefManager.getLoginStatus()){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()

            }else{
                if(prefManager.getLoginStatus()){
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()

                }/*else{
                    startActivity(Intent(this,MainActivity::class.java))
                }*/
            }
        },3000)
    }
}