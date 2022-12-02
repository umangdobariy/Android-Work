package com.example.amazon_new.Adoptors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazon_new.Modal.horizontal
import com.example.amazon_new.databinding.AutoscrollLayoutBinding
import com.example.amazon_new.databinding.HorizontalLayoutBinding

class horiz_Adoptor(var context: Context,var horizlist:MutableList<horizontal>)
    :RecyclerView.Adapter<horiz_Adoptor.Myview>(){
    lateinit var binding: HorizontalLayoutBinding

    class Myview(var bind: HorizontalLayoutBinding) : RecyclerView.ViewHolder(bind.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
        binding = HorizontalLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return Myview(binding)
    }

    override fun onBindViewHolder(holder: Myview, position: Int) {

        var hori = horizlist[position]
        holder.bind.ivImg.setImageResource(hori.Img)
        holder.bind.tvName.text = hori.Tital


    }
    override fun getItemCount(): Int {
        return horizlist.size
    }

}