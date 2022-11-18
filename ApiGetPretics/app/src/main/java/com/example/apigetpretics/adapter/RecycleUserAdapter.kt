package com.example.apigetpretics.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apigetpretics.R
import com.example.apigetpretics.databinding.ActivityMainBinding
import com.example.apigetpretics.databinding.ItemUserLayoutBinding
import com.example.apigetpretics.model.User

class RecycleUserAdapter(var activity:Activity,var userList:MutableList<User>): RecyclerView.Adapter<RecycleUserAdapter.MyViewHolder>() {

    lateinit var binding:ItemUserLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]

        holder.bind.tvName.text = "${user.fName} ${user.lName}"
        holder.bind.tvEmail.text = "${user.email}"


        Glide.with(activity).load(user.avatar).centerCrop().placeholder(R.drawable.ic_baseline_hourglass_bottom_24).into(holder.bind.ivThumbnail)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: ItemUserLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }

    fun setItems(userList: MutableList<User>)
    {
        this.userList = userList
        notifyDataSetChanged()
    }

}