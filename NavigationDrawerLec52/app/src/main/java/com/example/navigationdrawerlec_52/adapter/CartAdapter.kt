package com.example.jaybajrangbali.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.model.Profile
import com.example.jaybajrangbali.model.Profile1
import com.example.navigationdrawerlec_52.R
import com.example.navigationdrawerlec_52.adapter.Cart1Adapter
import com.example.navigationdrawerlec_52.databinding.CartListBinding


class CartAdapter(var activity: Activity,var cartList: MutableList<Profile>,var listener: Cart1Adapter.OnListItemProfileClickListener) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    lateinit var binding: CartListBinding

    class MyViewHolder(var binding: CartListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CartListBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var cart  = cartList[position]

        holder.binding.udThumbnail.setImageResource(R.drawable.ic_baseline_account_box_24)
        holder.binding.udHedar.text = cart.name
        holder.binding.udArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)

        holder.binding.parentk.setOnClickListener {
            listener.onItemClicked(it,cart)
        }


    }
    override fun getItemCount(): Int {
        return cartList.size
    }
}