package com.example.jaybajrangbali.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.jaybajrangbali.model.Categoryfilp
import com.example.navigationdrawerlec_52.activites.MainActivity
import com.example.navigationdrawerlec_52.databinding.ItemFilpBinding
import com.example.navigationdrawerlec_52.model.Item

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

        holder.bind.parentr.setOnClickListener {

            Toast.makeText(activity, "${categoryfilp.name}", Toast.LENGTH_SHORT).show()
            
            var intent = Intent(activity,MainActivity::class.java)
            activity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemView:ItemFilpBinding) : RecyclerView.ViewHolder(itemView.root) {
        var bind = itemView
    }
}