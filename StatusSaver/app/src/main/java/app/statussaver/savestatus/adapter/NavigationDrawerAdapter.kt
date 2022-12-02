package app.statussaver.savestatus.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.statussaver.R
import app.statussaver.databinding.ItemNavDrawerBinding
import app.statussaver.databinding.ItemSavestatusBinding
import app.statussaver.savestatus.model.NavigationModel

class NavigationDrawerAdapter(
    var context: Context,
    var listData: MutableList<NavigationModel>,
    //var Pos: Int
) : RecyclerView.Adapter<NavigationDrawerAdapter.ViewData>() {

    lateinit var binding: ItemNavDrawerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        binding = ItemNavDrawerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewData(binding)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        var itemList = listData[position]

        // if (position == Pos) {
        //    holder.itemView.setBackgroundResource(ContextCompat.getColor(context, R.color.black))
        // } else {
        //   holder.itemView.setBackgroundResource(ContextCompat.getColor(context,R.color.purple_200))
        // }
        //  holder.bind.ivIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        //holder.bind.tvTitle.text=setTextColor(Color.WHITE)
        //   holder.bind.tvTitle.setTextColor(Color.WHITE)

        holder.bind.tvTitle.text = itemList.title
        holder.bind.ivIcon.setImageResource(itemList.icon)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewData(ItemView: ItemNavDrawerBinding) : RecyclerView.ViewHolder(ItemView.root) {
        var bind = ItemView
    }
}