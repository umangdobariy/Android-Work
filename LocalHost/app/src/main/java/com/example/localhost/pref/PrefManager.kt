package com.example.localhost.pref

import android.content.Context
import android.content.IntentFilter
import com.example.localhost.model.User
import com.google.gson.Gson

class PrefManager( context: Context) {

    private val PREF_NAME = "student"
    private val LOGIN_STATUS = "login-status"
    private val USER = "user"

    private var sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    private var editor = sharedPreferences.edit()

    fun setLoginStatus(status:Boolean){
        editor.putBoolean(LOGIN_STATUS,status)
        editor.commit()
    }

    // convert user object to json string
    fun setUser(user: User){

        // convert user object to json string

        var  gson  =  Gson()
        var jsonString = gson.toJson(user)

          editor.putString(USER,jsonString)
          editor.commit()
    }

    // convert json string to user object
    fun getUser():User
    {
        var result = sharedPreferences.getString(USER,"")

        var gson = Gson()
        return gson.fromJson(result,User::class.java)

    }

    fun getLoginstatus():Boolean
    {
        return sharedPreferences.getBoolean(LOGIN_STATUS,false)
    }





}