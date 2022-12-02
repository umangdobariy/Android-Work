package app.project3.Adapter

import android.content.Context
import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import app.project3.ItemClickEvent
import app.project3.R
import app.project3.databinding.RowUserBinding
import app.project3.model.User

class UserAdapter(var context: Context, var userData: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewData>() {

    lateinit var binding: RowUserBinding
    lateinit var listener: ItemClickEvent


    fun onItemSeectedListner(listener: ItemClickEvent) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        binding = RowUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewData(binding)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        var listdata = userData[position]

        holder.itemData.tvName.text = listdata.name
        holder.itemData.tvEmail.text = listdata.email
        holder.itemData.tvContect.text = listdata.contect
        holder.itemData.tvPassword.text = listdata.password

        holder.itemData.cvItem.setOnClickListener {
            listener.onViewClick(position, listdata)

        }

        holder.itemData.ivSelection.setOnClickListener {
            showPopup(position, listdata, it, listener)
        }

    }

    fun showPopup(position: Int, user: User, view: View?, listener: ItemClickEvent) {
        var popupmenu = PopupMenu(context, view)
        var inflater: MenuInflater = popupmenu.menuInflater
        inflater.inflate(R.menu.menu, popupmenu.menu)
        popupmenu.show()

        popupmenu.setOnMenuItemClickListener(
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

    class ViewData(var  itemData: RowUserBinding) : RecyclerView.ViewHolder(itemData.root) {

        }

}