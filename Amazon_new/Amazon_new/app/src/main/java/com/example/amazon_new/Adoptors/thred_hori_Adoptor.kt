package com.example.amazon_new.Adoptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazon_new.Modal.thred_horizontal
import com.example.amazon_new.databinding.TherDemoLayoutBinding


class thred_hori_Adoptor(var context: Context,var thrdHorilist:MutableList<thred_horizontal>)
    :RecyclerView.Adapter<thred_hori_Adoptor.myviewHolder>() {
    lateinit var binding: TherDemoLayoutBinding


    class myviewHolder(var bind: TherDemoLayoutBinding) :RecyclerView.ViewHolder(bind.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        binding = TherDemoLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return myviewHolder(binding)
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
       var item = thrdHorilist[position]
        holder.bind.thredIv.setImageResource(item.Img)
        holder.bind.tvName.text = item.Name


    }

    override fun getItemCount(): Int {
        return  thrdHorilist.size
    }

}