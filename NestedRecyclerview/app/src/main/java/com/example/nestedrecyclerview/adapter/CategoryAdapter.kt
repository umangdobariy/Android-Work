package com.example.nestedrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ItemParentLayoutBinding
import com.example.nestedrecyclerview.modal.Category
import com.example.nestedrecyclerview.modal.SubCategory

class CategoryAdapter(var context: Context,var categoryList:MutableList<Category> ) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    lateinit var binding: ItemParentLayoutBinding
    lateinit var listener:ItemClickListener

    interface ItemClickListener{
        fun ViewAllClicked(id:Int,position: Int,title:String)
        fun OnChildItemClicked(view: View,subCategory: SubCategory)
    }

    fun  setOnItemClickListener(listener: ItemClickListener){
        this.listener = listener
    }

    class MyViewHolder (var binding: ItemParentLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemParentLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var category = categoryList[position]
        holder.binding.tvTitle.text = category.title

        var adapter = SubCategoryAdapter(context,category.movieList,listener)
        binding.childView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.childView.adapter = adapter

        holder.binding.btnView.setOnClickListener {
            listener.ViewAllClicked(category.id,position,category.title )
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}