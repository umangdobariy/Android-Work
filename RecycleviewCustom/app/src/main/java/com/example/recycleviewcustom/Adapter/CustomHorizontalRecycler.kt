package com.example.recycleviewcustom.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewcustom.Modal.Food9
import com.example.recycleviewcustom.Modal.movie
import com.example.recycleviewcustom.databinding.ItemCardLayoutBinding
import com.example.recycleviewcustom.databinding.ItemHorizontalItemLayoutBinding
import java.nio.file.Files.size

class CustomHorizontalRecycler(var activity: Activity, var movieList: MutableList<movie>): RecyclerView.Adapter<CustomHorizontalRecycler.MyviewHolder>() {

    lateinit var binding:ItemHorizontalItemLayoutBinding
    private lateinit var itemClickListener: onItemClickListener

    interface onItemClickListener{

        fun onItemclicked(view: View,movie: movie)
        fun onThumbnailSelected(view: View,movie: movie,position: Int)
    }

    fun setOnItemClickListener(itemClickListener: onItemClickListener){

        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        binding = ItemHorizontalItemLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return MyviewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {

        var movie = movieList[position]

        holder.bind.ivThumbnail.setImageResource(movie.image)
        holder.bind.tvName.text = movie.name
        holder.bind.rating.rating = movie.rating
        holder.bind.tvType.text = movie.type

        holder.bind.cardParent.setOnClickListener {
            itemClickListener.onItemclicked(it,movie)
        }

        holder.bind.ivThumbnail.setOnClickListener {
            itemClickListener.onThumbnailSelected(it,movie,position)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MyviewHolder(itemView: ItemHorizontalItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }
}