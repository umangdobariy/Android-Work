package com.example.food.Prefrence

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract

//create prefrence class

class Prefrence(context: Context) {

    companion object {


         val Pref_Name = "User_Record"

        //private val Email = "email"
        //private val Pass = "password"
        //private val Is_Login = "islogin"

        //signupdata
         val Name = "Name"
         val Email = "email"
         val Pass = "password"
         val cPass = "Cpassword"

         val Is_Login = "islogin"
    }

    //sherprefrence data
    private var sherprefrence = context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
    private var edits = sherprefrence.edit()

    //set data in parmiter
    fun SaveData(email: String, password: String) {
        edits.putString(Email, email)
        edits.putString(Pass, password)
        edits.commit()
    }

    fun SetLoginStatus(Status: Boolean) {
        edits.putBoolean(Is_Login, Status)
        edits.commit()
    }


    fun Logout() {
        edits.clear()
        edits.commit()
    }

    fun GetData(): Array<String> {
        var name = sherprefrence.getString(Email, "")
        var email = sherprefrence.getString(Pass, "")

        return arrayOf(Email, Pass)

    }


    //Sign up data
    fun SaveSignUp(sname: String, semail: String, sPassword: String, sCpassword: String) {
        edits.putString(Name, sname)
        edits.putString(Email, semail)
        edits.putString(Pass, sPassword)
        edits.putString(cPass, sCpassword)
        edits.commit()
    }


    fun getPrefData(name:String):String?{
        return sherprefrence.getString(name,null)
    }


    //fun getData(name: String) :Array<String>{
    /*fun getData(email1: String, password: String) {
        //sherprefrence.getString(Name, name1)
        sherprefrence.getString(Email, email1)
        sherprefrence.getString(Pass, password)
        // sherprefrence.getString(cPass, cPassword)

        // return arrayOf(Name,Email,Pass,cPass)
    }*/

    fun getLoginStatus(): Boolean {
        return sherprefrence.getBoolean(Is_Login, false)
        //   sherprefrence.edit()
    }

}