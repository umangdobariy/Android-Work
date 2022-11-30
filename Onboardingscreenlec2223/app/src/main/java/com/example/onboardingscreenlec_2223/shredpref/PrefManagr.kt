package com.example.mahadev.shredpref

import android.content.Context
import android.content.SharedPreferences

class PrefManagr(var context: Context) {

    val PEREF_NAME = "my-pref"
    val KEY_STATUS =  "isLogin"
    val KEY_INTRO = "isIntro"

    var sharedPreferences:SharedPreferences =  context.getSharedPreferences(PEREF_NAME,Context.MODE_PRIVATE)
    var editor:SharedPreferences.Editor = sharedPreferences.edit()

    fun  setLoginStatus(status:Boolean){
        editor.putBoolean(KEY_STATUS,status)
        editor.commit()
    }

    fun getLoginStatus():Boolean{
        return sharedPreferences.getBoolean(KEY_STATUS,false)
    }

    fun setIntrostatus(status: Boolean){
        editor.putBoolean(KEY_INTRO,status)
        editor.commit()
    }

    fun getIntroStatus():Boolean{
        return sharedPreferences.getBoolean(KEY_INTRO,false)
    }

    fun logout(){
        editor.remove(KEY_STATUS)
        editor.commit()
    }
}