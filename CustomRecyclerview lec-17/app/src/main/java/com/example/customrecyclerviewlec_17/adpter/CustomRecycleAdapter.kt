package com.example.customrecyclerviewlec_17.adpter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.customrecyclerviewlec_17.Modal.Food
import com.example.customrecyclerviewlec_17.databinding.ItemCardLayoutBinding
import com.example.customrecyclerviewlec_17.listener.OnListitemClickListener

class CustomRecycleAdapter(var activity:Activity,var itemList: MutableList<Food>): RecyclerView.Adapter<CustomRecycleAdapter.myviewHolder>() {

    lateinit var binding: ItemCardLayoutBinding
    private lateinit var listener: OnListitemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        binding = ItemCardLayoutBinding.inflate(LayoutInflater.from(activity),parent,false)
        return myviewHolder(binding)
    }

        fun setOnlistItemClickListener(listener: OnListitemClickListener){
        this.listener = listener
    }


    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        var Food = itemList[position]

        holder.bind.udName.text = Food.Name
        holder.bind.udType.text = Food.type
        holder.bind.udPrice.text = "${Food.Price}"
        holder.bind.udYear.text = "${Food.year}"
        holder.bind.udRating.rating = Food.rating
        holder.bind.udThumbnail.setImageResource(Food.image)

        holder.bind.llview.setOnClickListener {
            Toast.makeText(activity, "Image : $position", Toast.LENGTH_SHORT).show()
            listener.onImageClicked(it,position)
        }

        holder.bind.parent.setOnClickListener {

            Toast.makeText(activity, "${Food.Name}", Toast.LENGTH_SHORT).show()

            listener.onFoodClicked(position,Food)

           // var intent = Intent(activity,HomeActivity::class.java)
           // activity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
         return itemList.size
    }

    class myviewHolder(itemView: ItemCardLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var bind = itemView
    }
}