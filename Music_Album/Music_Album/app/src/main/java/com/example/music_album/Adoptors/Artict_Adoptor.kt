package com.example.music_album.Adoptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_album.Modal.Aritct
import com.example.music_album.databinding.ArtictLayoutBinding

class Artict_Adoptor(var context: Context,var artictlist:MutableList<Aritct>):RecyclerView.Adapter<Artict_Adoptor.innerviewholder>(){

    private lateinit var binding: ArtictLayoutBinding

    class innerviewholder(var  bind :ArtictLayoutBinding) :RecyclerView.ViewHolder(bind.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innerviewholder {

     binding = ArtictLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return innerviewholder(binding)

    }

    override fun onBindViewHolder(holder: innerviewholder, position: Int) {

        var artict = artictlist[position]
        holder.bind.ivIg.setImageResource(artict.Img)
        holder.bind.tvTit.text = artict.Tital

    }

    override fun getItemCount(): Int {

        return artictlist.size
    }


}