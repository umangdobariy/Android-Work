package com.example.getpretics.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.getpretics.databinding.CustomUserLayoutBinding
import com.example.getpretics.model.User

class RecycleUserAdapter(var activity:Activity,var userList: MutableList<User>) : RecyclerView.Adapter<RecycleUserAdapter.MyViewHolder>() {

    lateinit var binding: CustomUserLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomUserLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]

        holder.bind.uvName.text = "${user.fName} ${user.lName}"
        holder.bind.uvEmail.text = "${user.email}"

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: CustomUserLayoutBinding) : RecyclerView.ViewHolder(itemView.root)
    {
        var bind = itemView
    }

    fun  setItems(userList: MutableList<User>)
    {
        this.userList = userList
        notifyDataSetChanged()
    }
}