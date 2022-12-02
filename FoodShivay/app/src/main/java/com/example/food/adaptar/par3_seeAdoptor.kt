package com.example.food.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.Interface.OnParClickeListner
import com.example.food.R
import com.example.food.databinding.Par3SeeallBinding
import com.example.food.modal.home
import com.example.food.modal.par3_seeall

class par3_seeAdoptor (var context: Context, var p3seeall:MutableList<par3_seeall>,var listner:OnParClickeListner)
    :RecyclerView.Adapter<par3_seeAdoptor.innersee>() {

    lateinit var binding:Par3SeeallBinding
    class innersee(var bind: Par3SeeallBinding) : RecyclerView.ViewHolder(bind.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innersee {
        binding = Par3SeeallBinding.inflate(LayoutInflater.from(context),parent,false)
        return innersee(binding)

    }

    override fun onBindViewHolder(holder: innersee, position: Int) {
        var p3see = p3seeall[position]
        holder.bind.ivP3.setImageResource(p3see.Img)
        holder.bind.lacationN.text = p3see.locn
        holder.bind.rating.rating = p3see.rating
        holder.bind.resN.text = p3see.recto
        holder.bind.timeN.text = p3see.Time

       holder.bind.cantiner.setOnClickListener {
           listner.childClicke(it,p3see,position)
       }

    }

    override fun getItemCount(): Int {
        return p3seeall.size

    }
/*
    //create function for filter data
    fun filteredlist(filterlist: MutableList<par3_seeall>) {

        this.p3seeall = filterlist
        notifyDataSetChanged()
    }*/
}


