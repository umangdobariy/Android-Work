package com.example.food.adaptar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.Interface.OnHoriClickeListner
import com.example.food.R
import com.example.food.databinding.HorizontalListBinding
import com.example.food.modal.home
import com.example.food.modal.hor_list

class hori_adaptor(var context: Context,var horilist:MutableList<hor_list>):RecyclerView.Adapter<hori_adaptor.innerhori>() {

    lateinit var binding: HorizontalListBinding
    lateinit var horiclick:OnHoriClickeListner
    var row_Index = -1


    class innerhori(var bind:HorizontalListBinding) :RecyclerView.ViewHolder(bind.root){

    }
    fun clickelistner(horiClickeListner: OnHoriClickeListner){
        this.horiclick = horiClickeListner
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innerhori {
        binding = HorizontalListBinding.inflate(LayoutInflater.from(context),parent,false)
        return innerhori(binding)
    }

    override fun onBindViewHolder(holder: innerhori, position: Int) {
        var hori = horilist[position]
        holder.bind.HImg.setImageResource(hori.Img)

        holder.bind.container.setOnClickListener {
            horiclick.horilistner(hori,position)
            row_Index=position
           notifyDataSetChanged()


            //horiclick.horilistner(hori,position)

        }
        if (row_Index==position){
            holder.bind.container.setBackgroundColor(Color.parseColor("#51AC2C5D"))
        }else{
            holder.bind.container.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }

    }

    override fun getItemCount(): Int {
       return  horilist.size
    }

/*
    //create function for filter data
    fun filteredlist(filterlist: MutableList<hor_list>) {

        this.horilist = filterlist
        notifyDataSetChanged()
    }*/

}