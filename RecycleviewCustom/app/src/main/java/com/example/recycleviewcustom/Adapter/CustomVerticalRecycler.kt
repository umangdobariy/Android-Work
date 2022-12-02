package com.example.recycleviewcustom.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewcustom.HomeActivity
import com.example.recycleviewcustom.MainActivity
import com.example.recycleviewcustom.Modal.Food9
import com.example.recycleviewcustom.Modal.movie
import com.example.recycleviewcustom.databinding.ItemCardLayoutBinding

class CustomVerticalRecycler(var activity: Activity, var itemList: MutableList<Food9>): RecyclerView.Adapter<CustomVerticalRecycler.MyviewHolder>() {

    lateinit var binding: ItemCardLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        binding = ItemCardLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyviewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {

        var food9 = itemList[position]

        holder.bind.udImage.setImageResource(food9.image)
        holder.bind.udName.text = food9.name
        holder.bind.udMrp.text = "${food9.mrp}"
        holder.bind.udRating.rating = food9.rating
        holder.bind.udYear.text = "${food9.year}"

        holder.bind.cardFirst.setOnClickListener {

            Toast.makeText(activity, "${food9.name}.", Toast.LENGTH_SHORT).show()


            var intent = Intent(activity,MainActivity::class.java)
            activity.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyviewHolder(itemView: ItemCardLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }
}