package com.example.crude_opretion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.HandlerThread
import com.example.crude_opretion.databinding.ActivitySplshScreenBinding
import com.example.crude_opretion.shareprefrence.prefrence
import java.util.logging.Handler
import kotlin.concurrent.thread

class splshScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplshScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplshScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


         init()


    }
    fun init(){

      /*  android.os.Handler().postDelayed({

            var manger  = prefrence(this)
            if (manger.getlogin()){
                startActivity(Intent(applicationContext,homeActivity::class.java))
                finish()
            }else{
                //if(manger.getlogin()){
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                    finish()
               // }
            }

        },1000)


*/

        Thread(Runnable{
            //set time in splsh screen
            Thread.sleep(3000)

            //set manger
            var manger = prefrence(this)

            if (manger.getlogin()){
                //navigration to home activity
                startActivity(Intent(applicationContext,homeActivity::class.java))

            }else{
                //navigration to login activity
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        }).start()






    }



}