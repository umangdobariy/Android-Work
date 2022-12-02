package com.example.jaybajrangbali.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.databinding.ItemParentLayoutBinding
import com.example.jaybajrangbali.model.Category

class CategoryAdapter (var context: Context,var categoryList:MutableList<Category>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    lateinit var binding :ItemParentLayoutBinding

    class MyViewHolder(var binding: ItemParentLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemParentLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var category1 = categoryList[position]
        holder.binding.tvTitle.text = category1.title

         var childAdapter = SubCategoryAdapter(context,category1.foodList)
        binding.childView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.childView.adapter = childAdapter



    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}