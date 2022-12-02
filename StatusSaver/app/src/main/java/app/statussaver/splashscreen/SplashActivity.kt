package app.statussaver.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import app.statussaver.R
import app.statussaver.home.HomeActivity
import app.statussaver.savestatus.statusActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initview()
    }

    fun initview(){

        Thread(
            Runnable {
                Thread.sleep(1000)
                startActivity(Intent(this,statusActivity::class.java))
            }
        ).start()

    }
}