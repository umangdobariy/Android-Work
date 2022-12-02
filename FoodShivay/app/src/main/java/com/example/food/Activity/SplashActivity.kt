package com.example.food.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.food.Prefrence.Prefrence
import com.example.food.R

import com.example.food.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

           /// Thread(Runnable {
              //  Thread.sleep(500)

        Handler().postDelayed({
            var manager=Prefrence(this)

             if(!manager.getLoginStatus()){
                var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else if(manager.getLoginStatus()){
                startActivity(Intent(applicationContext,HomeActivity::class.java))
            }
            else{
                //startActivity(Intent(applicationContext,::class.java))
                Toast.makeText(this, "Login SucessFully", Toast.LENGTH_SHORT).show()
            }

        },500)



                //navigration to On Bodding Activity

          //  }).start()

    }
}