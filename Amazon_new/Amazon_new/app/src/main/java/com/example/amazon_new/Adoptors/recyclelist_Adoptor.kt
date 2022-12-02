package com.example.amazon_new.Adoptors

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amazon_new.Modal.recyclelist
import com.example.amazon_new.databinding.RecycleLayoutBinding

class recyclelist_Adoptor(var context: Context,var recyclelist:MutableList<recyclelist>)
    :RecyclerView.Adapter<recyclelist_Adoptor.Myviewholder>(){

    lateinit var binding: RecycleLayoutBinding

    class Myviewholder(var bind:RecycleLayoutBinding) :RecyclerView.ViewHolder(bind.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
      binding = RecycleLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return Myviewholder(binding)
    }


    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        var rec = recyclelist[position]

        //horizontal valu adoptor
        var Hmanager = horiz_Adoptor(context,rec.horizontallist)
        binding.HoriView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.HoriView.adapter = Hmanager

        //autoscroll valu adoptor
        var Amanager =auto_Adoptor(context,rec.autoscroll)
        binding.AutoView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.AutoView.adapter = Amanager

        var item = Amanager.itemCount
        //auto slider
        val handler = Handler()
        val runnable = object : Runnable {
            var count = 0
            var flag = true

            override fun run() {
                if(count==4){
                    count=-1
                }
               if (count < item) {
                    if (count == item-1) {
                       flag = false
                    }
                    else if (count == 0) {
                        flag = true
                    }
                    if (flag){
                        count++
                    } else{
                        count--

                    }
                    binding.AutoView.smoothScrollToPosition(count)
                    handler.postDelayed(this, 2200.toLong())

                }
            }

        }
        handler.postDelayed(runnable, 2200.toLong())

    }
    override fun getItemCount(): Int {
        return recyclelist.size
    }




}