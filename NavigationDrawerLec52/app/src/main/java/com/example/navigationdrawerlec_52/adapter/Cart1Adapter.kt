package com.example.navigationdrawerlec_52.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.adapter.CartAdapter
import com.example.jaybajrangbali.adapter.CategoryAdapter
import com.example.jaybajrangbali.model.Profile
import com.example.jaybajrangbali.model.Profile1
import com.example.navigationdrawerlec_52.databinding.Cart1ListBinding

class Cart1Adapter(var activity: Activity, var itemList: MutableList<Profile1>) : RecyclerView.Adapter<Cart1Adapter.MyViewHolder>() {

    lateinit var binding: Cart1ListBinding
    private lateinit var listener: OnListItemProfileClickListener


    interface OnListItemProfileClickListener {

        fun onCardClicked(view: Int, position: Int, title: String, cartList: MutableList<Profile>)
        fun onItemClicked(view:View,profile: Profile)
    }


    fun setOnListItemClickListener(listener: OnListItemProfileClickListener){
        this.listener = listener
    }


    class MyViewHolder(var binding: Cart1ListBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = Cart1ListBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var item = itemList[position]
        holder.binding.tvText.text = item.title

        var childAdapter = CartAdapter(activity,item.cartList,listener)
        binding.childView4.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding.childView4.adapter = childAdapter


        holder.binding.parentu.setOnClickListener {
            listener.onCardClicked(item.id,position,item.title,item.cartList)
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}