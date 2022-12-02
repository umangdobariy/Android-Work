package com.example.broadcastreceiverlec_57

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var myBroadCastReceiver = MyBroadCastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intenetFilter = IntentFilter()
        intenetFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(myBroadCastReceiver,intenetFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (myBroadCastReceiver!=null)
            unregisterReceiver(myBroadCastReceiver)
    }

}