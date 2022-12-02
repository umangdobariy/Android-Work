package com.example.clickeventadapter.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.clickeventadapter.databinding.FoodLayoutBinding
import com.example.clickeventadapter.listener.OnListitemClickListner
import com.example.clickeventadapter.modal.Food

class CustomHorizontalAdapter(var activity: Activity,var foodList: MutableList<Food>) : RecyclerView.Adapter<CustomHorizontalAdapter.MyViewHolder>() {

    lateinit var binding: FoodLayoutBinding

    private lateinit var listener: OnListitemClickListner

    fun setOnlistItemClickListener(listener: OnListitemClickListner){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = FoodLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var food = foodList[position]

        holder.bind.udName.text = food.name
        holder.bind.uvImage.setImageResource(food.image)

        holder.bind.parent1.setOnClickListener {
            listener.onCardClicked(position,food)
        }

        holder.bind.uvImage.setOnClickListener {
            listener.onImageClicked(it,position)
        }

    }

    override fun getItemCount(): Int {
       return foodList.size
    }

    class MyViewHolder(itemView: FoodLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }
}