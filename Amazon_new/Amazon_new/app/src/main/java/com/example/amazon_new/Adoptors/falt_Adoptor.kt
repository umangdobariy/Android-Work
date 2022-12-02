package com.example.amazon_new.Adoptors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazon_new.Modal.falt
import com.example.amazon_new.databinding.FaltLayoutBinding


class falt_Adoptor(var context: Context, var faltlist:MutableList<falt>)
    :RecyclerView.Adapter<falt_Adoptor.myfviewHolder>() {
    lateinit var binding:FaltLayoutBinding


    class myfviewHolder(var bind: FaltLayoutBinding) :RecyclerView.ViewHolder(bind.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myfviewHolder {
        binding = FaltLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return myfviewHolder(binding)
    }

    override fun onBindViewHolder(holder: myfviewHolder, position: Int) {
        var item = faltlist[position]
        holder.bind.ivIvg.setImageResource(item.img)
        holder.bind.tvTital.text = item.tital




    }

    override fun getItemCount(): Int {
        return  faltlist.size
    }

}