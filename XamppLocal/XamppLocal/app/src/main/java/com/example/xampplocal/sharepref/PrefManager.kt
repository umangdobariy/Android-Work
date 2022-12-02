package com.example.xampplocal.sharepref

import android.content.Context
import android.content.SharedPreferences
import com.example.xampplocal.model.User
import com.google.gson.Gson

class PrefManager(context:Context) {

    private val PREF_NAME = "student"
   private val KEY_STATUS="isLogin"
    private val USER  ="user"

    var sharePref:SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    var editor:SharedPreferences.Editor=  sharePref.edit()


    fun setLoginStatus(status:Boolean){
        editor.putBoolean(KEY_STATUS,status)
        editor.commit()
    }
    fun setUser(user:User){// convert user object to json string
        var gson = Gson()
        var userString = gson.toJson(user)
        //editor.putString(USER,userString)
        editor.putString(USER,userString)
        editor.commit()
    }


    fun getLoginStatus():Boolean{
        return sharePref.getBoolean(KEY_STATUS,false)
    }
    fun getUser(): User{ //convert json string to user object
        var result = sharePref.getString(USER,"")
        var gson = Gson()
        return gson.fromJson(result,User::class.java)// SQL -> sharepref -> recyclview
    }

    /*fun logout(){
        editor.remove(KEY_STATUS)
        editor.commit()
    }*/
}