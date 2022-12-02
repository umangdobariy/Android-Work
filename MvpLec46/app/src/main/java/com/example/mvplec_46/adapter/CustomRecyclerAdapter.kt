package com.example.mvplec_46.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.jsonparshinglec_3536.modal.User
import com.example.mvplec_46.R
import com.example.mvplec_46.databinding.ItemUserLayoutBinding


class CustomRecyclerAdapter(var activity: Activity, var userList: MutableList<User>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    lateinit var binding: ItemUserLayoutBinding
    lateinit var listener: OnClickListener


    interface OnClickListener {
        fun onItemClicked(user: User, position: Int)
    }

    fun setOnItemClickListener(listener: OnClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(activity), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var user = userList[position]

        holder.bind.tvName.text = "${user.fName} ${user.lName}"
        holder.bind.tvEmail.text = user.email

        Glide
            .with(activity)
            .load(user.avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_person_24)
            .into(holder.bind.ivProfile)

        holder.bind.parent.setOnClickListener {
            listener.onItemClicked(user , position)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: ItemUserLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        var bind = itemView
    }

    fun setItems(userList:MutableList<User>)
    {
        this.userList = userList
        notifyDataSetChanged()
    }

}