package com.example.jaybajrangbali.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.databinding.ItemFilpBinding
import com.example.jaybajrangbali.model.Categoryfilp

class FilpAdapter(var activity: Activity,var itemList:MutableList<Categoryfilp>): RecyclerView.Adapter<FilpAdapter.MyViewHolder>() {

    lateinit var binding: ItemFilpBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemFilpBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var categoryfilp = itemList[position]

        holder.bind.ivName.text = categoryfilp.name
        holder.bind.profileImage.setImageResource(categoryfilp.image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemView:ItemFilpBinding) : RecyclerView.ViewHolder(itemView.root) {
        var bind = itemView
    }
}