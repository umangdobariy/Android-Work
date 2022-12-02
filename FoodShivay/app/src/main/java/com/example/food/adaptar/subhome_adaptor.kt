package com.example.food.adaptar


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.Interface.OnHomeClickeListner
import com.example.food.databinding.FavListLayoutBinding
import com.example.food.modal.home
import com.example.food.modal.sub_home
import java.util.function.Function

//child adoptor
class subhome_adaptor(
    var context: Context,
    var Subhomelist: MutableList<sub_home>,
    var listner: OnHomeClickeListner

) : RecyclerView.Adapter<subhome_adaptor.innersubhome>() {


    //item layout
    lateinit var binding: FavListLayoutBinding

    class innersubhome(var bind: FavListLayoutBinding) : RecyclerView.ViewHolder(bind.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innersubhome {

        binding = FavListLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return innersubhome(binding)

    }

    override fun onBindViewHolder(holder: innersubhome, position: Int) {

        //data geting
        var shome = Subhomelist[position]

        var Img = holder.bind.ivImg.setImageResource(shome.img)


        holder.bind.tvTit.text = shome.Tit




        holder.bind.cardView.setOnClickListener {

            this.listner.homelistlistner(it, shome, position)

        }

    }


    override fun getItemCount(): Int {
        return Subhomelist.size
    }

     //create function for filter data
     fun filteredlist(filterlist: MutableList<sub_home>) {
         this.Subhomelist = filterlist
         notifyDataSetChanged()
     }

}