package com.example.clickeventadapter.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clickeventadapter.MainActivity
import com.example.clickeventadapter.databinding.ItemLayoutBinding
import com.example.clickeventadapter.listener.OnListitemClickListner
import com.example.clickeventadapter.modal.User

class CustomVerticalAdapter(var activity:Activity,var userList: MutableList<User>): RecyclerView.Adapter<CustomVerticalAdapter.MyViewHolder>() {

    lateinit var binding: ItemLayoutBinding



    class MyViewHolder(itemView: ItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      binding = ItemLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var user = userList[position]

        holder.bind.udName.text = user.name
        holder.bind.uvImage.setImageResource(user.image)

        holder.bind.parent.setOnClickListener {

            Toast.makeText(activity, "${user.name}", Toast.LENGTH_SHORT).show()

            var intent = Intent(activity,MainActivity::class.java)
            activity.startActivity(intent)
        }

        holder.bind.uvImage.setOnClickListener {

            Toast.makeText(activity, "${user.image}", Toast.LENGTH_SHORT).show()


        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}