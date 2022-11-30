package com.example.onboardingscreenlec_2223.adpter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingscreenlec_2223.HomeActivity
import com.example.onboardingscreenlec_2223.MainActivity
import com.example.onboardingscreenlec_2223.ShowActivity
import com.example.onboardingscreenlec_2223.databinding.ItemCardLayoutBinding
import com.example.onboardingscreenlec_2223.modal.food

class CustomVerticalAdapter(var activity: Activity,var itemList:MutableList<food>):

    RecyclerView.Adapter<CustomVerticalAdapter.MyViewHolder>() {

    private lateinit var binding: ItemCardLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemCardLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var muser = itemList[position]
        holder.mbind.udName.text = muser.name
        holder.mbind.udPrice.text = "${muser.price}"
        holder.mbind.udRating.rating = muser.rating
        holder.mbind.udThumbnail.setImageResource(muser.image)
        holder.mbind.udYear.text = "${muser.year}"

        holder.mbind.parent.setOnClickListener {

            var intent = Intent(activity,ShowActivity::class.java)
            activity.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemView: ItemCardLayoutBinding):RecyclerView.ViewHolder(itemView.root){
        var mbind = itemView
    }

}