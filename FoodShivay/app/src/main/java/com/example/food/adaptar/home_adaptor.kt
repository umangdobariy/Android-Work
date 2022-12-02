package com.example.food.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food.Interface.OnHomeClickeListner
import com.example.food.databinding.TitalLayoutBinding
import com.example.food.modal.home
import com.example.food.modal.sub_home
import kotlin.coroutines.coroutineContext

//class home_adaptor(var context: Context, var homelist: MutableList<home>) :
class home_adaptor() : RecyclerView.Adapter<home_adaptor.innerhome>() {

    lateinit var binding: TitalLayoutBinding

    lateinit var context: Context
    lateinit var homelist: MutableList<home>

    constructor(context: Context, homelistdata: MutableList<home>) : this() {
        this.context = context
        this.homelist = homelistdata
    }


    lateinit var homeclicke: OnHomeClickeListner
    lateinit var adoptor: subhome_adaptor


    class innerhome(var bind: TitalLayoutBinding) : RecyclerView.ViewHolder(bind.root) {


    }

    fun homelistner(homeClickeListner: OnHomeClickeListner) {
        this.homeclicke = homeClickeListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innerhome {
        binding = TitalLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return innerhome(binding)

    }

    override fun onBindViewHolder(holder: innerhome, position: Int) {
        var home = homelist[position]
        holder.bind.tvTital.text = home.tital
        //home clicek event


        //sub home list

        adoptor = subhome_adaptor(context, home.subHome, homeclicke)
        binding.childRec.layoutManager = GridLayoutManager(
            context, 2,
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.childRec.adapter = adoptor
         adoptor.notifyDataSetChanged()


    }


    fun func(text: String) {

        var filterlist = mutableListOf<sub_home>()

        for (grid in adoptor.Subhomelist) {
            if (grid.Tit.toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(grid)
            }
        }
        if (filterlist.isEmpty()) {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        } else {
            adoptor.filteredlist(filterlist)
        }

    }


    override fun getItemCount(): Int {
        return homelist.size

    }


}





