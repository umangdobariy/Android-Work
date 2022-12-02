package com.example.amazon_new.Adoptors

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amazon_new.Modal.recyclelist
import com.example.amazon_new.Modal.recyclelist2
import com.example.amazon_new.databinding.RecycleLayoutBinding
import com.example.amazon_new.databinding.TherdHorizontalRecycleViewBinding

class recyclelist2_Adoptor(var context: Context, var recyclelist2:MutableList<recyclelist2>)
    :RecyclerView.Adapter<recyclelist2_Adoptor.myviewHolder2>(){
    lateinit var binding: TherdHorizontalRecycleViewBinding

    class myviewHolder2(var bind:TherdHorizontalRecycleViewBinding) :RecyclerView.ViewHolder(bind.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder2 {
       binding = TherdHorizontalRecycleViewBinding.inflate(LayoutInflater.from(context),parent,false)

        return myviewHolder2(binding)
    }

    override fun onBindViewHolder(holder: myviewHolder2, position: Int) {
        var rec2 = recyclelist2[position]

        // first valu
        var ThrdAdoptor = thred_hori_Adoptor(context,rec2.thredHori)
        holder.bind.thredRecview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        holder.bind.thredRecview.adapter = ThrdAdoptor

        //second valu
        var flatAdoptor = falt_Adoptor(context,rec2.faltlist)
        holder.bind.thredRecview2.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        holder.bind.thredRecview2.adapter = flatAdoptor

    }

    override fun getItemCount(): Int {
        return  recyclelist2.size

    }

}