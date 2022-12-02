package com.example.crd.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crd.database.entity.User
import com.example.crd.databinding.CustomUserLayoutBinding
import com.example.crd.listener.OnListItemClickListner

class RecycleUserAdapter(var activity:Activity,var userList: MutableList<User>): RecyclerView.Adapter<RecycleUserAdapter.MyViewHolder>() {

    lateinit var binding: CustomUserLayoutBinding
    lateinit var onClickListener:OnListItemClickListner

    fun  setOnListItemClickListner(onClickListener: OnListItemClickListner)
    {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomUserLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]

        holder.bind.kvId.text = "${user.id}"
        holder.bind.kvName.text = "${user.name}"
        holder.bind.kvEmail.text = "${user.email}"


        holder.bind.rvDelete.setOnClickListener {
            onClickListener.onDeleteItemClicked(user)
        }

        holder.bind.rvUpdate.setOnClickListener {
            onClickListener.onUpdateItemClicked(user)
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