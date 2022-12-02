package com.example.preticsadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.preticsadapter.adapter.CustomVerticalAdapter
import com.example.preticsadapter.databinding.ActivityMainBinding
import com.example.preticsadapter.modal.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userList = mutableListOf<User>()
    private lateinit var adapter:CustomVerticalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preparadata()

        adapter = CustomVerticalAdapter(this,userList)

        var manager = LinearLayoutManager(this)
        binding.recyclerviewItem.layoutManager = manager


        binding.recyclerviewItem.adapter = adapter
    }

    private fun preparadata() {

        userList.add(User(1,"umang",R.drawable.image1))
        userList.add(User(1,"vipul",R.drawable.image2))
        userList.add(User(1,"sager",R.drawable.image3))
        userList.add(User(1,"amit",R.drawable.image4))

    }
}