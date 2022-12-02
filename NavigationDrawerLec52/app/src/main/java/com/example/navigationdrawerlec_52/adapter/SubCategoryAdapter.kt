package com.example.jaybajrangbali.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jaybajrangbali.model.Subcategory
import com.example.navigationdrawerlec_52.databinding.ItemChildLayoutBinding

class SubCategoryAdapter(var context: Context, var subcategoryList: MutableList<Subcategory>,var listener: CategoryAdapter.ItemClickListener) : RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {

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

        holder.binding.parentm.setOnClickListener {
            listener.onChildItemClicked(it,subcategory)
        }

    }

    override fun getItemCount(): Int {
        return subcategoryList.size
    }
}