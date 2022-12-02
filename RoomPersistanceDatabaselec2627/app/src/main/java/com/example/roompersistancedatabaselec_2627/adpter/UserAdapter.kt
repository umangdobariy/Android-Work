package com.example.roompersistancedatabaselec_2627.adpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roompersistancedatabaselec_2627.UserActivity
import com.example.roompersistancedatabaselec_2627.database.Appdatabase
import com.example.roompersistancedatabaselec_2627.database.dao.UserDAO
import com.example.roompersistancedatabaselec_2627.database.entity.User
import com.example.roompersistancedatabaselec_2627.databinding.CustomDilogBinding
import com.example.roompersistancedatabaselec_2627.databinding.UserLayoutBinding


class UserAdapter(var context: Context, var uList: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var db:Appdatabase?=null

    class MyViewHolder(val binding: UserLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        db = Room.databaseBuilder(
            context,Appdatabase::class.java, "tops_student").allowMainThreadQueries().build()

        return MyViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var user = uList[position]

        holder.binding.tvName.text = user.name
        holder.binding.tvEmail.text = user.email
        holder.binding.tvContact.text = user.contact
       holder.binding.tvId.text = "${user.id}"


//        holder.binding.parentCard.setOnClickListener {
//            var intent = Intent(context, UserActivity::class.java)
//            intent.putExtra("USER", user)
//            context.startActivity(intent)
//        }

         holder.binding.parentCard.setOnClickListener {
           //  var intent=Intent(context,UserActivity::class.java)
           //  intent.putExtra("USER",user)
          //   context.startActivity(intent)
         }

         holder.binding.ivAdd.setOnClickListener {

             var builder = AlertDialog.Builder(context)
             var bind =  CustomDilogBinding.inflate(LayoutInflater.from(context))
             builder.setView(bind.root)

             var dialog = builder.create()
             dialog.show()

             bind.tvDelete.setOnClickListener {
                 deleteItem(position)
                 dialog.dismiss()
             }

             bind.tvUpdate.setOnClickListener {
                  var intent = Intent(context, UserActivity::class.java)
                  intent.putExtra("USER", user)
                  context.startActivity(intent)
                  dialog.dismiss()
             }

         }

    }

    fun deleteItem(index: Int){
        db?.userDao?.delete(uList.get(index))
        uList.removeAt(index)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = uList.size

    fun setItems(userList:MutableList<User>)
    {
        this.uList = userList
        notifyDataSetChanged()      // refresh recyclerview list item
    }
}