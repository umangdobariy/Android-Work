package com.example.u1.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.u1.Modal.user
import com.example.u1.databinding.UserListBinding

class CustomListAdapter(var context: Context,var UserList: MutableList<user>):BaseAdapter() {

    lateinit var binding: UserListBinding

    override fun getCount(): Int {
        return UserList.size
    }

    override fun getItem(pos: Int): Any {
        return UserList[pos]
    }

    override fun getItemId(pos: Int): Long {
        return  pos.toLong()
    }

    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View {
        binding = UserListBinding.inflate(LayoutInflater.from(context),parent,false)

        var user = UserList[pos]
        binding.rdProfile.setImageResource(user.image)
        binding.rdName.text = user.name
        binding.rdNumber.text = "${user.contact}"
        binding.rdCity.text = user.city


        return binding.root
    }
}