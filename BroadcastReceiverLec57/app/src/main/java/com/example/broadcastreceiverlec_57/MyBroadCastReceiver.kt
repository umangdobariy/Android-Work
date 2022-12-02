package com.example.broadcastreceiverlec_57

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log

class MyBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.let {
            if (it.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {

                if (isAirplaneModeOn(context!!)) {

                    Log.d("MYAPP", "onReceive: MyApp : Airplane mode :ON ")

                } else {
                    Log.d("MYAPP", "onReceive:MyApp:Airplane mode : OFF ")
                }

            }
        }
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.System.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) !== 0
    }
}