package com.example.jaybajrangbali.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.databinding.ItemParentLayout3Binding
import com.example.jaybajrangbali.model.Category2
import com.example.jaybajrangbali.model.Category3

class CategoryAdapter3(var context: Context, var categoryList3: MutableList<Category3>)
    : RecyclerView.Adapter<CategoryAdapter3.MyViewHolder>() {

    lateinit var binding3 :ItemParentLayout3Binding

    class MyViewHolder(var binding3: ItemParentLayout3Binding) : RecyclerView.ViewHolder(binding3.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding3 = ItemParentLayout3Binding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding3)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var category3 = categoryList3[position]
        holder.binding3.tvTitle3.text = category3.title3

         var childAdapter = SubCategoryAdapter3(context,category3.itemList)
        binding3.childView3.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding3.childView3.adapter = childAdapter



    }

    override fun getItemCount(): Int {
        return categoryList3.size
    }
}