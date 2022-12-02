package com.example.nestedrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ItemChildLayoutBinding
import com.example.nestedrecyclerview.modal.SubCategory
import java.nio.file.Files.size

class SubCategoryAdapter(var context: Context, var subCategoryList:MutableList<SubCategory>,var listener:CategoryAdapter.ItemClickListener) : RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {

    lateinit var binding: ItemChildLayoutBinding

    class MyViewHolder(var binding: ItemChildLayoutBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemChildLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var subCategory = subCategoryList[position]

        holder.binding.tvTitle.text = subCategory.name
        holder.binding.ivThumbnail.setImageResource(subCategory.image)
        holder.binding.rating.rating = subCategory.rating

        holder.binding.parent.setOnClickListener {
            listener.OnChildItemClicked(it,subCategory)
        }
    }

    override fun getItemCount(): Int {
        return subCategoryList.size
    }
}