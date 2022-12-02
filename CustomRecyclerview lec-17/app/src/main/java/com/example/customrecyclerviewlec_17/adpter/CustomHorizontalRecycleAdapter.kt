package com.example.customrecyclerviewlec_17.adpter

import android.app.Activity
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customrecyclerviewlec_17.Modal.movie

import com.example.customrecyclerviewlec_17.databinding.ItemHorizontalItemLayoutBinding
import com.example.customrecyclerviewlec_17.listener.OnListitemClickListener

class CustomHorizontalRecycleAdapter(var activity:Activity, var movieList: MutableList<movie>): RecyclerView.Adapter<CustomHorizontalRecycleAdapter.myviewHolder>() {

    lateinit var binding: ItemHorizontalItemLayoutBinding

    private lateinit var listener: OnListitemClickListener

    fun setOnlistItemClickListener(listener: OnListitemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        binding = ItemHorizontalItemLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return myviewHolder(binding)
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        var movie = movieList[position]

        holder.bind.tvTitle.text = movie.title
        holder.bind.tvType.text = movie.type
        holder.bind.rating.rating = movie.rating
        holder.bind.ivThumbnail.setImageResource(movie.image)


        holder.bind.ivThumbnail.setOnClickListener {
            listener.onImageClicked(it, position)
        }

//        holder.bind.parent.setOnClickListener {
//
//            listener.onCardClicked(position,movie)
//
//        }

    }

    override fun getItemCount(): Int {
         return movieList.size
    }

        class myviewHolder(itemView: ItemHorizontalItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }
}