package com.example.jaybajrangbali.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.databinding.ItemChildLayout3Binding
import com.example.jaybajrangbali.databinding.ItemChildLayoutBinding
import com.example.jaybajrangbali.databinding.ItemParentLayout3Binding
import com.example.jaybajrangbali.databinding.ItemParentLayoutBinding

import com.example.jaybajrangbali.model.Subcategory
import com.example.jaybajrangbali.model.Subcategory2
import com.example.jaybajrangbali.model.Subcategory3

class SubCategoryAdapter3(var context: Context, var subcategoryList3: MutableList<Subcategory3>) : RecyclerView.Adapter<SubCategoryAdapter3.MyViewHolder>() {

    lateinit var binding :ItemChildLayout3Binding

    class MyViewHolder(var binding: ItemChildLayout3Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemChildLayout3Binding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var subcategory3 = subcategoryList3[position]

        holder.binding.uvTitle3.text = subcategory3.name
        holder.binding.ivThumbnail3.setImageResource(subcategory3.image)
        holder.binding.rating3.rating = subcategory3.rating

    }

    override fun getItemCount(): Int {
        return subcategoryList3.size
    }
}