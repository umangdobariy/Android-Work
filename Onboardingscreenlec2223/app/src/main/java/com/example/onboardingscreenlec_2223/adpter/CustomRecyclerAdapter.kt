package com.example.onboardingscreenlec_2223.adpter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingscreenlec_2223.databinding.MovieHorizontalListBinding
import com.example.onboardingscreenlec_2223.modal.movie

class CustomRecyclerAdapter(var activity:Activity,var movieList:MutableList<movie>):RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    lateinit var binding: MovieHorizontalListBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = MovieHorizontalListBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = movieList[position]
        holder.bind.tvTitle.text = user.title
        holder.bind.tvType.text = user.typ
        holder.bind.tvImage.setImageResource(user.image)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    class MyViewHolder(itemView: MovieHorizontalListBinding): RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }
}