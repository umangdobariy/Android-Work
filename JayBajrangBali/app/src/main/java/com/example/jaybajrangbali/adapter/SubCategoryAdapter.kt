package com.example.jaybajrangbali.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.databinding.ItemChildLayoutBinding
import com.example.jaybajrangbali.databinding.ItemParentLayoutBinding

import com.example.jaybajrangbali.model.Subcategory
import com.example.jaybajrangbali.model.Subcategory2
import com.example.jaybajrangbali.model.Subcategory3

class SubCategoryAdapter(var context: Context, var subcategoryList: MutableList<Subcategory>) : RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {

    lateinit var binding :ItemChildLayoutBinding

    class MyViewHolder(var binding: ItemChildLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemChildLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var subcategory = subcategoryList[position]

        holder.binding.uvTitle.text = subcategory.name
        holder.binding.ivThumbnail.setImageResource(subcategory.image)
        holder.binding.rating.rating = subcategory.rating

    }

    override fun getItemCount(): Int {
        return subcategoryList.size
    }
}