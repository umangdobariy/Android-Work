package com.example.food.adaptar

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food.Interface.OnClickListner
import com.example.food.R
import com.example.food.databinding.GridDemoLayoutBinding
import com.example.food.modal.gridclass
import kotlinx.coroutines.NonDisposableHandle.parent

class grid_Adaptor(var context: Context, var gridlist:MutableList<gridclass>)
    :RecyclerView.Adapter<grid_Adaptor.innersee>() {

    lateinit var binding:GridDemoLayoutBinding
    lateinit var OnClicke:OnClickListner

   fun  OngridListner(onClickListner: OnClickListner){
        this.OnClicke = onClickListner
    }

    class innersee(var bind: GridDemoLayoutBinding) : RecyclerView.ViewHolder(bind.root){

        //serchview
        var name:TextView = bind.tvName
        var img:ImageView = bind.ivIg


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innersee {
        binding = GridDemoLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return innersee(binding)

    }


    override fun onBindViewHolder(holder: innersee, position: Int) {
        var grid = gridlist[position]
        holder.bind.ivIg.setImageResource(gridlist.get(position).Img)
        holder.bind.tvName.text = gridlist.get(position).name

        holder.bind.ivIg.setOnClickListener{

          OnClicke.gridviewclicke(grid,position)

        }



    }

    override fun getItemCount(): Int {
        return gridlist.size

    }
    //create function for filter data
    fun filteredlist(filterlist: MutableList<gridclass>){

        this.gridlist = filterlist
        notifyDataSetChanged()
    }
//    fun setfilter(gridlist: MutableList<gridclass>){
//        gridlist.clear()
//        gridlist.addAll(gridlist)
//        notifyDataSetChanged()
//    }

}


