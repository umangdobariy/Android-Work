package com.example.music_album.Adoptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_album.Modal.Album
import com.example.music_album.databinding.MusicLayoutBinding
import com.squareup.picasso.Picasso

class Music_Adoptor (var context: Context,var musiclist:MutableList<Album>):RecyclerView.Adapter<Music_Adoptor.myviewholder>(){
    lateinit var binding: MusicLayoutBinding

    class myviewholder(var bind: MusicLayoutBinding) :RecyclerView.ViewHolder(bind.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
       binding = MusicLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return myviewholder(binding)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        var song = musiclist[position]

        //holder.bind.ivImg.setImageResource(song.Img)
        Picasso.get().load(song.Img).into(holder.bind.ivImg)

       // holder.bind.ivImg.setImageResource(song.Img)
        holder.bind.tvTital.text = song.name

    }

    override fun getItemCount(): Int {
        return  musiclist.size

    }

}