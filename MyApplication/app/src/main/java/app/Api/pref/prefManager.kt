package app.Api.pref

import android.content.Context
import app.Api.model.User
import com.google.gson.Gson

class prefManager(var context: Context) {

    var PREF_NAME = "Student"
    var Login_Status = "login"
    var USER = "user"

    var sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun isLoginStatus(status: Boolean) {
        editor.putBoolean(Login_Status, status)
        editor.commit()
    }


    fun setUser(user: User) {

        //convert user object ot json string

        var gson = Gson()
        var jsonString = gson.toJson(user)

        editor.putString(USER, jsonString)
        editor.commit()

    }

    fun getUser(): User {

        var user: User? = null
        var result = sharedPreferences.getString(USER, "")

        var gson = Gson()
        return gson.fromJson(result, User::class.java)

    }

    fun getLoginStatus():Boolean{
        return sharedPreferences.getBoolean(Login_Status,false)
    }

}