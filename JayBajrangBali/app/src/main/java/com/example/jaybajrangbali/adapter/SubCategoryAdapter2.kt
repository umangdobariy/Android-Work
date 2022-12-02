package com.example.jaybajrangbali.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.databinding.ItemChildLayout2Binding
import com.example.jaybajrangbali.databinding.ItemChildLayoutBinding
import com.example.jaybajrangbali.databinding.ItemParentLayoutBinding

import com.example.jaybajrangbali.model.Subcategory
import com.example.jaybajrangbali.model.Subcategory2

class SubCategoryAdapter2 (var context: Context, var subcategoryList2:MutableList<Subcategory2>) : RecyclerView.Adapter<SubCategoryAdapter2.MyViewHolder>() {

    lateinit var binding :ItemChildLayout2Binding

    class MyViewHolder(var binding: ItemChildLayout2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemChildLayout2Binding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var subcategory2 = subcategoryList2[position]

        holder.binding.uvTitle2.text = subcategory2.name
        holder.binding.ivThumbnail2.setImageResource(subcategory2.image)
        holder.binding.rating2.rating = subcategory2.rating

    }

    override fun getItemCount(): Int {
        return subcategoryList2.size
    }
}