package com.example.preticsadapter.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.preticsadapter.databinding.ItemLayoutBinding
import com.example.preticsadapter.modal.User

class CustomVerticalAdapter(var activity:Activity,var userList: MutableList<User>) : RecyclerView.Adapter<CustomVerticalAdapter.MyViewHolder>() {

    lateinit var binding: ItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]

        holder.bind.uvImage.setImageResource(user.image)
        holder.bind.udName.text =user.name

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: ItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        var bind = itemView
    }
}