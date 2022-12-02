package com.example.appstopandcontinue

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var TAG = "ACTIVITY_LIFECYCLE";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"onCreate :called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart:called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop:called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: called")
    }
}