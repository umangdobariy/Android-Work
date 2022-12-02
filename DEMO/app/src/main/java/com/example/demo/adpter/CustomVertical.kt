package com.example.demo.adpter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.HomeActivity
import com.example.demo.modal.Food

class CustomVertical(var activity:Activity,var itemList: MutableList<Food>): RecyclerView.Adapter<CustomVertical.myViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}