package com.example.crude_opretion.adaptor

import android.content.Context
import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.crude_opretion.ClickeEvent.onclicklistner
import com.example.crude_opretion.R
import com.example.crude_opretion.databinding.ActivityRagisterBinding
import com.example.crude_opretion.databinding.RecyclerViewLayoutBinding
import com.example.crude_opretion.modal.User
import com.example.crude_opretion.modal.studentlist

class rec_Adaptor(var context: Context, var reclist: MutableList<User>) :
    RecyclerView.Adapter<rec_Adaptor.rec_holder>() {

    lateinit var binding: RecyclerViewLayoutBinding
    lateinit var clickelistner: onclicklistner

    class rec_holder(var bind: RecyclerViewLayoutBinding) : RecyclerView.ViewHolder(bind.root) {

    }

    fun Clickelistner(clicke: onclicklistner) {
        this.clickelistner = clicke
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rec_holder {
        binding = RecyclerViewLayoutBinding.inflate(LayoutInflater.from(context), parent, false)

        return rec_holder(binding)
    }

    override fun onBindViewHolder(holder: rec_holder, position: Int) {
        var data = reclist[position]
        holder.bind.tvName.text = data.name
        holder.bind.tvEmail.text = data.email
        holder.bind.tvContect.text = data.mobile
        holder.bind.tvPassword.text = data.pass

        holder.bind.ivSelection.setOnClickListener {
            //showpopup menu
            showpopup(position, data, it, clickelistner)


            clickelistner.clickeevent(data, position)
        }

    }

    //showpopup menu
    private fun showpopup(position: Int, data: User, it: View?, clickelistner: onclicklistner) {

        var popupMenu = PopupMenu(context, it)
        var inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener(object :PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(p0: MenuItem?): Boolean {
                return when (p0?.itemId) {

                    R.id.update -> {
                        clickelistner.update(data, position)
                        return true
                    }
                    R.id.delete -> {
                        clickelistner.delate(data)
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
        return reclist.size
    }

    fun setItem(arraydata: MutableList<User>) {
        this.reclist = arraydata
        notifyDataSetChanged()
    }

    fun update(data: User,position: Int){
        reclist[position]=data
        notifyDataSetChanged()
    }

    fun delete(data: User){
        reclist.remove(data)
        notifyDataSetChanged()
    }

}