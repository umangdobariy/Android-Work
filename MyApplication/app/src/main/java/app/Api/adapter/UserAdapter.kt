package app.Api.adapter

import android.content.Context
import android.view.*

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import app.Api.ItemClickEvent
import app.Api.R
import app.Api.databinding.RowUserBinding
import app.Api.model.User


class UserAdapter(var context: Context, var userData:MutableList<User>):
    RecyclerView.Adapter<UserAdapter.ViewData>() {

    lateinit var binding: RowUserBinding
    lateinit var listener: ItemClickEvent

    fun onItemSeclected(listener:ItemClickEvent){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        binding = RowUserBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewData(binding)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
       var list=userData.get(position)

       holder.itemData.tvName.text=list.name
       holder.itemData.tvEmail.text=list.email
       holder.itemData.tvPassword.text=list.password
       holder.itemData.tvContect.text=list.contect


        holder.itemData.cvItem.setOnClickListener {
                listener.onViewClick(position,list)
        }

        holder.itemData.ivSelection.setOnClickListener {
            showPopup(position, list, it, listener)
        }
    }

    fun showPopup(position: Int,user: User,view: View?,listener:ItemClickEvent){

        var popupMenu = PopupMenu(context,view)
        var inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener(
            object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {

                    return when (p0?.itemId) {
                        R.id.action_Update -> {
                            listener.onItemUpdate(position, user)
                            return true
                        }
                        R.id.action_Delete -> {
                            listener.onItemDelete(user)
                            return true
                        }
                        else -> {
                            return false
                        }

                    }
                }
            })
    }


    override fun getItemCount(): Int {
        return userData.size
    }


    fun setItem(arryData: MutableList<User>) {
        this.userData = arryData
        notifyDataSetChanged()
    }

    fun delete(user: User) {
        userData.remove(user)
        notifyDataSetChanged()
    }

    fun updatedata(position: Int, user: User) {
        //userData[position]=user
        userData[position]=user
        //userData.add(position,user)
        notifyItemChanged(position)
        //notifyDataSetChanged()
    }

    class ViewData(var itemData:RowUserBinding) : RecyclerView.ViewHolder(itemData.root){

    }



}
