package com.example.food.Interface

import android.icu.text.Transliterator
import android.view.View
import com.example.food.modal.par3_seeall

interface OnParClickeListner {

    fun Seeall(id:Int,position:Int,Tital:String)
    fun childClicke(view: View,seeall:par3_seeall,position: Int)

}