package com.example.food.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.SeeAllActivityBinding
import com.example.food.modal.gridclass
import com.example.food.modal.see_all

class See_All_Adaptar(var context: Context, var seealllist: MutableList<see_all>) :
    RecyclerView.Adapter<See_All_Adaptar.seeall>(){

    lateinit var binding: SeeAllActivityBinding

    class seeall(var bind: SeeAllActivityBinding) : RecyclerView.ViewHolder(bind.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): seeall {

          binding = SeeAllActivityBinding.inflate(LayoutInflater.from(context), parent, false)
          return seeall(binding)

    }

    override fun onBindViewHolder(holder: seeall, position: Int) {

        var p3see = seealllist[position]

        holder.bind.ivP3.setImageResource(p3see.Imge)
        holder.bind.lacationN.text = p3see.locn
        holder.bind.rating.rating = p3see.ratinge
        holder.bind.resN.text = p3see.recto
        holder.bind.timeN.text = p3see.Times

    }

    override fun getItemCount(): Int {

        return seealllist.size
    }
    //create function for filter data
    fun filteredlist(filterlist: MutableList<see_all>){

        this.seealllist = filterlist
        notifyDataSetChanged()

    }

}