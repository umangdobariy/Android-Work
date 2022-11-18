package com.example.roomcredoperation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcredoperation.database.entity.User
import com.example.roomcredoperation.databinding.CustomUserLayoutBinding
import com.example.roomcredoperation.listner.OnListItemClickListner

class RecycleUserAdapter(var activity:Activity,var userList: MutableList<User> ): RecyclerView.Adapter<RecycleUserAdapter.MyViewHolder>() {

    lateinit var binding: CustomUserLayoutBinding
    private lateinit var onClickListner: OnListItemClickListner

    fun setOnListItemClickListner(onClickListner: OnListItemClickListner)
    {
        this.onClickListner = onClickListner
    }



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomUserLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]

        holder.bind.uvId.text = "${user.id}"
        holder.bind.tvName.text = "${user.name}"
        holder.bind.tvEmail.text = "${user.email}"

        holder.bind.udDelete.setOnClickListener {
            onClickListner.OnDeleteItem(user)
        }

        holder.bind.udUpdate.setOnClickListener {
            onClickListner.OnupdateItem(user)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: CustomUserLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }

    fun setItems(userList: MutableList<User>)
    {
        this.userList = userList
        notifyDataSetChanged()
    }
}