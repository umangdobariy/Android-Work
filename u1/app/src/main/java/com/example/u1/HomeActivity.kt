package com.example.u1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListAdapter
import com.example.u1.Adapter.CustomListAdapter
import com.example.u1.Modal.user
import com.example.u1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var UserList = mutableListOf<user>()
    private lateinit var adapter: CustomListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preparaData()

        adapter = CustomListAdapter(this,UserList)

        binding.listItem.adapter = adapter

    }

    private fun preparaData() {
        UserList.add(user(R.drawable.image1,"Umang",123,"Jamnagar"))
        UserList.add(user(R.drawable.image2,"Vishal",256,"Mumbai"))
        UserList.add(user(R.drawable.image3,"Montu",748,"Surat"))
        UserList.add(user(R.drawable.image4,"Milan",963,"Rajkot"))
        UserList.add(user(R.drawable.image5,"Ravi",512,"Valsad"))
    }
}