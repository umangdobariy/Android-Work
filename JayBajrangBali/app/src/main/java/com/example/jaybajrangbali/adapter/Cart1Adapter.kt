package com.example.jaybajrangbali.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.databinding.Cart1ListBinding

import com.example.jaybajrangbali.model.Profile1

class Cart1Adapter(var activity: Activity, var itemList: MutableList<Profile1>) : RecyclerView.Adapter<Cart1Adapter.MyViewHolder>() {

    lateinit var binding: Cart1ListBinding

    class MyViewHolder(var binding: Cart1ListBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = Cart1ListBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var item = itemList[position]
        holder.binding.tvText.text = item.title

        var childAdapter = CartAdapter(activity,item.cartList)
        binding.childView4.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding.childView4.adapter = childAdapter

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}