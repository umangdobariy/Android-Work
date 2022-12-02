package app.project3.postdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import app.project3.R
import app.project3.pref.prefManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    fun init() {

        Handler().postDelayed({
            var prefManager = prefManager(this)

            if (!prefManager.getLoginStatus()) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                if (prefManager.getLoginStatus()) {
                    startActivity(Intent(this, HomeAcitvity::class.java))
                    finish()
                }
            }

        }, 1000)

    }
}