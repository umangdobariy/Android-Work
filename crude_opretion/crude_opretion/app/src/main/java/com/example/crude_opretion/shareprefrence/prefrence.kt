package com.example.crude_opretion.shareprefrence

import android.content.Context
import android.content.SharedPreferences
import com.example.crude_opretion.modal.User
import com.google.gson.Gson

class prefrence(var context: Context) {

    var pref_name = "Student"
    var Login_Status = "login"
    private val Uuser = "User"

    var sharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun isLogin(status: Boolean) {
        editor.putBoolean(Login_Status, status)
        editor.commit()
    }

    fun getlogin(): Boolean {
        return sharedPreferences.getBoolean(Login_Status, false)
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }

    fun datasave(user: User) {

        //conver json to gson
        var gson = Gson()
        var jsonString = gson.toJson(user)

        editor.putString(Uuser, jsonString)
        editor.commit()


    }

    fun getdata(): User? {

        var user: User? = null
        var result = sharedPreferences.getString(Uuser, "")

        //string to json
        var gson = Gson()
        return gson.fromJson(result, User::class.java)

    }

}