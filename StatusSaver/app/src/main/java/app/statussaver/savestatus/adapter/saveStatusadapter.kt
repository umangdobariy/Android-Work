package app.statussaver.savestatus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.statussaver.databinding.ItemSavestatusBinding
import app.statussaver.savestatus.model.saveStatusModel
import com.bumptech.glide.Glide

class saveStatusadapter(var context:Context,var listSaveStatus:MutableList<saveStatusModel>) :RecyclerView.Adapter<saveStatusadapter.ViewData>() {

    lateinit var binding:ItemSavestatusBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
           binding= ItemSavestatusBinding.inflate(LayoutInflater.from(context),parent,false)
           return ViewData(binding)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        var listimg=listSaveStatus[position]
        Glide.with(context).load(listimg.img).into(holder.bind.ivPlayIcon)
    }

    override fun getItemCount(): Int {
        return listSaveStatus.size
    }


    class ViewData(itemView:ItemSavestatusBinding):RecyclerView.ViewHolder(itemView.root){
          var bind=itemView
    }
}