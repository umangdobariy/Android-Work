package com.example.myfristapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class FristActivity : AppCompatActivity() {

    private var TAG = "ACITVITY_LIFECYCLE";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frist)

        Log.d(TAG,"on create:called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"on Start:called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"on Stop:called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"on Destroy:called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"on Restart:called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"on Resume:called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"on Pause:called")
    }

}