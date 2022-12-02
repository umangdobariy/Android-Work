package app.Api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import app.Api.pref.prefManager

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    fun init(){

        Handler().postDelayed({

            var prefManager = prefManager(this)

           if(!prefManager.getLoginStatus()) {
               startActivity(Intent(this,LoginActivity::class.java))
               finish()
           }else {
                if (prefManager.getLoginStatus()){
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()
                }
           }


        },1000)
    }
}