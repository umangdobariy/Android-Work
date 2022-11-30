package com.example.splashfragmentlec_8

import android.app.Activity
import android.content.Context
import android.widget.Toast

class Utils (){


    lateinit var context:Context

    fun customToast(name:String){
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
    }



}