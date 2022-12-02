package com.example.food.adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food.Activity.Seeall_Activity
import com.example.food.Interface.OnParClickeListner
import com.example.food.databinding.Par3TitleBinding
import com.example.food.databinding.TitalLayoutBinding
import com.example.food.modal.home
import com.example.food.modal.par3_tital

class par3_titAdoptor(var context: Context, var p3titlist:MutableList<par3_tital>)
    : RecyclerView.Adapter<par3_titAdoptor.innertit>() {


        lateinit var binding:Par3TitleBinding
        lateinit var clickeListner: OnParClickeListner
        class innertit(var bind: Par3TitleBinding) : RecyclerView.ViewHolder(bind.root){

        }

        fun clickeEvent(clickeListner: OnParClickeListner){
            this.clickeListner = clickeListner
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innertit {
            binding = Par3TitleBinding.inflate(LayoutInflater.from(context),parent,false)
            return innertit(binding)

        }

        override fun onBindViewHolder(holder: innertit, position: Int) {
            var p3tital = p3titlist[position]
            holder.bind.tvTital.text = p3tital.tital

            //clicke event
            holder.bind.btnSeeall.setOnClickListener {
                 clickeListner.Seeall(p3tital.id,position,p3tital.tital)
               // var intent = Intent(context,Seeall_Activity::class.java)


            }

            //sub par3 see all
            var p3Adoptor= par3_seeAdoptor(context,p3tital.seelall,clickeListner)

            binding.childRec3.layoutManager= LinearLayoutManager(context)
            binding.childRec3.adapter = p3Adoptor




        }

        override fun getItemCount(): Int {
            return p3titlist.size

        }
/*

    //create function for filter data
    fun filteredlist(filterlist: MutableList<par3_tital>) {

        this.p3titlist = filterlist
        notifyDataSetChanged()
    }
*/



    }


