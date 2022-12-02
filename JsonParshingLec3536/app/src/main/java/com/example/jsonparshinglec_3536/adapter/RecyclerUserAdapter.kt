package com.app.jsonparsingandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jsonparshinglec_3536.R
import com.example.jsonparshinglec_3536.databinding.ItemUserLayoutBinding
import com.example.jsonparshinglec_3536.modal.User


class RecyclerUserAdapter(var context: Context, var userListt: MutableList<User>) :
    RecyclerView.Adapter<RecyclerUserAdapter.MyViewHolder>() {
    private lateinit var binding: ItemUserLayoutBinding


    class MyViewHolder(var bind: ItemUserLayoutBinding) : RecyclerView.ViewHolder(bind.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var user = userListt[position]

        holder.bind.tvName.text = "${user.fName} ${user.lName}"
        holder.bind.tvEmail.text = "${user.email}"
        //holder.bind.ivThumbnail.setImageResource(user.image)

        Glide.with(context).load(user.avatar).centerCrop().placeholder(R.drawable.ic_baseline_hourglass_full_24).into(holder.bind.ivThumbnail)

        holder.bind.cardParent.setOnClickListener {

        }

        holder.bind.ivThumbnail.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return userListt.size
    }

    fun setItems(userList: MutableList<User>){
       userListt.addAll(userList)
        notifyDataSetChanged()
    }

}