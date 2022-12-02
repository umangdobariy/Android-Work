package com.example.xampplocal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.xampplocal.R
import com.example.xampplocal.databinding.UserLayoutBinding
import com.example.xampplocal.model.User


class UserAdapter(val context: Context, var uList:MutableList<User>):RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    class MyViewHolder(val binding:UserLayoutBinding):RecyclerView.ViewHolder(binding.root)

    lateinit var listener:ItemClickListner

    interface ItemClickListner {
        fun onViewClicked(pos: Int, user:User)
        fun onUpdateButtonClicked(pos: Int, user: User)
        fun onDeleteButtonClicked(user: User)
    }
    fun onItemSelected(listener:ItemClickListner){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
   return MyViewHolder(
       UserLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
   )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var user = uList[position]
        holder.binding.tvName.text = user.name
        holder.binding.tvContact.text = user.contact
        holder.binding.tvEmail.text = user.email

        holder.binding.parentCard.setOnClickListener {
            listener.onViewClicked(position,user)
        }

        holder.binding.ivMenu.setOnClickListener {
            showPopupMenu(position, user, it, listener)
        }
    }

    private fun showPopupMenu(pos: Int, user: User, view: View?, listener: ItemClickListner) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.item_menu, popup.menu)
        popup.show()

        popup.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_update -> {
                    listener.onUpdateButtonClicked(pos, user)
                    true
                }
                R.id.menu_delete -> {
                    listener.onDeleteButtonClicked(user)
                    true
                }
                else -> false
            }
        }

    }

    override fun getItemCount() = uList.size

    fun deleteData(user:User){
        uList.remove(user)
        notifyDataSetChanged()
    }
    fun updateRecord(user: User, pos: Int) {
        uList[pos] = user
        notifyItemChanged(pos)
    }
   fun setItem(newList:MutableList<User>){
       uList =newList
        notifyDataSetChanged()
    }
}
