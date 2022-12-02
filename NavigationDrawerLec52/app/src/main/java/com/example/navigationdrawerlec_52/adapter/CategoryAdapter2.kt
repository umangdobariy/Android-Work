package com.example.jaybajrangbali.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.model.Category2
import com.example.navigationdrawerlec_52.databinding.ItemParentLayout2Binding

class CategoryAdapter2 (var context: Context, var categoryList2:MutableList<Category2>) :
    RecyclerView.Adapter<CategoryAdapter2.MyViewHolder>() {

    lateinit var binding2 :ItemParentLayout2Binding

    class MyViewHolder(var binding2: ItemParentLayout2Binding) : RecyclerView.ViewHolder(binding2.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding2 = ItemParentLayout2Binding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding2)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var category2 = categoryList2[position]
        holder.binding2.tvTitle2.text = category2.title2

         var childAdapter2 = SubCategoryAdapter2(context,category2.menuList)
        binding2.childView2.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding2.childView2.adapter = childAdapter2



    }

    override fun getItemCount(): Int {
        return categoryList2.size
    }
}