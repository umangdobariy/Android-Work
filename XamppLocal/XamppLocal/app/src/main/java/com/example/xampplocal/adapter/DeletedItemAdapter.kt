package com.example.xampplocal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.xampplocal.R
import com.example.xampplocal.databinding.DeleteUserLayoutBinding
import com.example.xampplocal.model.User

class DeletedItemAdapter (var context: Context,var dList:MutableList<User>):RecyclerView.Adapter<DeletedItemAdapter.MyViewHolder>(){

    class MyViewHolder(var binding:DeleteUserLayoutBinding):RecyclerView.ViewHolder(binding.root)
    lateinit var listener:ItemClickListner

    interface ItemClickListner {
        fun onUndoButtonClicked(pos: Int, user: User)
        fun onDeleteButtonClicked(user: User)
    }
    fun onItemSelected(listener:ItemClickListner){
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(
           DeleteUserLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
       )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = dList[position]
       holder.binding.tvEmail.text= user.email
       holder.binding.tvName.text= user.name
       holder.binding.tvContact.text= user.contact
       holder.binding.ivMenu.setOnClickListener {
           showPopupMenu(position, user, it, listener)
       }

    }

    override fun getItemCount(): Int  = dList.size

    private fun showPopupMenu(pos: Int, user: User, view: View?, listener: ItemClickListner) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.delete_item_menu, popup.menu)
        popup.show()

        popup.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_undo -> {
                    listener.onUndoButtonClicked(pos, user)
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
    fun setItem(newList:MutableList<User>){
        dList =newList
        notifyDataSetChanged()
    }

    fun deleteData(user:User){
        dList.remove(user)
        notifyDataSetChanged()
    }

}