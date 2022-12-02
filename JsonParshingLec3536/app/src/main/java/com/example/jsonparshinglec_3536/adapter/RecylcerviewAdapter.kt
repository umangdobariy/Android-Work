package com.example.jsonparshinglec_3536.adapter

import android.content.Context
import android.os.Handler

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jsonparshinglec_3536.Constant.ConstantData
import com.example.jsonparshinglec_3536.R
import com.example.jsonparshinglec_3536.databinding.ItemUserLayoutBinding
import com.example.jsonparshinglec_3536.databinding.ProgressLodingBinding
import com.example.jsonparshinglec_3536.modal.User


class RecylcerviewAdapter(var context: Context, var UserList: ArrayList<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var bindingItem: ItemUserLayoutBinding
    lateinit var bindingProgress: ProgressLodingBinding


    class ItemViewHolder(var itemUser: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(itemUser.root)

    class LoadingViewHolder(var itemProgress: ProgressLodingBinding) :
        RecyclerView.ViewHolder(itemProgress.root)

    fun addData(dataString: ArrayList<User>) {
        this.UserList.addAll(dataString)
        notifyDataSetChanged()
    }

    fun getItemAtPostion(position: Int):User{
        return  UserList[position]
    }

    fun addLoadingView(){
        Handler().post{
            // UserList.addAll()
            notifyItemChanged(UserList.size-1)
        }
    }

    fun removeLoadingView(){
        if(UserList.size!=0){
          UserList.removeAt(UserList.size-1)
          notifyItemRemoved(UserList.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return if (viewType == ConstantData.View_Type_Item) {
            bindingItem = ItemUserLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
            ItemViewHolder(bindingItem)
        } else {
            bindingProgress =
                ProgressLodingBinding.inflate(LayoutInflater.from(context), parent, false)
            LoadingViewHolder(bindingProgress)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var user = UserList[position]

        if (holder.itemViewType == ConstantData.View_Type_Item) {

            //holder.tvName.text = "${user.fName} ${user.lName}"
            //holder.bind.tvEmail.text = "${user.email}"
            //holder.bind.ivThumbnail.setImageResource(user.image)

            // Glide.with(context).load(user.avatar).centerCrop().placeholder(R.drawable.ic_baseline_hourglass_full_24).into(holder.bind.ivThumbnail)

            bindingItem.tvName.text = "${user.fName} ${user.lName}"
            bindingItem.tvEmail.text = "${user.email}"
            //bindingItem.ivThumbnail.setImageResource(user.image)

            Glide.with(context).load(user.avatar).centerCrop()
                .placeholder(R.drawable.ic_baseline_hourglass_full_24).into(bindingItem.ivThumbnail)

        }
    }

    override fun getItemCount(): Int {
        return UserList.size
    }

    override fun getItemViewType(position: Int): Int {
        //return super.getItemViewType(position)
        return if (UserList[position] == null) {
            ConstantData.View_Type_Loading
        } else {
            ConstantData.View_Type_Item
        }
    }

    fun setItems(userList: ArrayList<User>){
        this.UserList = userList
        notifyDataSetChanged()
    }
}