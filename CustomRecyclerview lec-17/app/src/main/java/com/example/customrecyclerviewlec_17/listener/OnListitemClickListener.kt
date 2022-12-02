package com.example.customrecyclerviewlec_17.listener

import android.view.View
import com.example.customrecyclerviewlec_17.Modal.Food
import com.example.customrecyclerviewlec_17.Modal.movie

interface OnListitemClickListener {

    fun onCardClicked(pos:Int,movie: movie)
    fun onImageClicked(view: View, pos: Int)

    fun onFoodClicked(pos:Int,food: Food)


}