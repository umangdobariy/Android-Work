package com.example.clickeventadapter.listener


import android.view.View
import com.example.clickeventadapter.modal.Food


interface OnListitemClickListner {

    fun onCardClicked(pos:Int,food: Food)
    fun onImageClicked(view: View, pos: Int)
}