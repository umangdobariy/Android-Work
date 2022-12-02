package com.example.amazon_new.Adoptors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amazon_new.Modal.autoscroll
import com.example.amazon_new.databinding.AutoscrollLayoutBinding

class auto_Adoptor(var context: Context, var autolist:MutableList<autoscroll>)
    :RecyclerView.Adapter<auto_Adoptor.myAview>() {
    lateinit var binding: AutoscrollLayoutBinding

    class myAview(var bind: AutoscrollLayoutBinding) :RecyclerView.ViewHolder(bind.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAview {
        binding = AutoscrollLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return myAview(binding)
    }



        /*override fun onBindViewHolder(holder: thred_hori.myView, position: Int) {



*//*
            var Tadoptor =thred_hori(context,auto.thredlist)
            binding.payRecview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            binding.payRecview.adapter = Tadoptor*//*

        }


*/


    override fun getItemCount(): Int {
      return autolist.size
    }

    override fun onBindViewHolder(holder: myAview, position: Int) {
        var auto = autolist[position]
        holder.bind.ivIg.setImageResource(auto.Img)

    }


}