package com.example.interview24_11_2022.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interview24_11_2022.database.entity.User
import com.example.interview24_11_2022.databinding.CustomUserLayoutBinding
import com.example.interview24_11_2022.listner.OnIteemClickListner

class RecyclerAdapter(var activity:Activity,var userList:MutableList<User>) :RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    lateinit var binding: CustomUserLayoutBinding

    lateinit var OnClickListner:OnIteemClickListner

    @JvmName("setOnClickListner1")
    fun setOnClickListner(OnClickListner:OnIteemClickListner)
    {
        this.OnClickListner = OnClickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = CustomUserLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]

        holder.bind.uksId .text = "${user.id}"
        holder.bind.uksName.text = "${user.name}"
        holder.bind.uksEmail.text = "${user.email}"


        holder.bind.uvDelete.setOnClickListener {
            OnClickListner.OnDeleteItem(user)
        }

        holder.bind.uvEdit.setOnClickListener {
            OnClickListner.OnUpdateItem(user)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: CustomUserLayoutBinding) : RecyclerView.ViewHolder(itemView.root)
    {
            var bind = itemView
    }

    fun setItems(userList: MutableList<User>)
    {
        this.userList = userList
        notifyDataSetChanged()
    }

}