package com.example.clickeventadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickeventadapter.adapter.CustomHorizontalAdapter
import com.example.clickeventadapter.adapter.CustomVerticalAdapter
import com.example.clickeventadapter.databinding.ActivityMainBinding
import com.example.clickeventadapter.listener.OnListitemClickListner
import com.example.clickeventadapter.modal.Food
import com.example.clickeventadapter.modal.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userList = mutableListOf<User>()
    private lateinit var adapter: CustomVerticalAdapter
    private var foodList = mutableListOf<Food>()
    private lateinit var madapter:CustomHorizontalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // vertical adapter
        adapter = CustomVerticalAdapter(this,userList)

        var manager = LinearLayoutManager(this)
        binding.recyclerviewItem.layoutManager = manager

        binding.recyclerviewItem.adapter = adapter





        // horizontal Adapter
        madapter = CustomHorizontalAdapter(this,foodList)

        manager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerviewFood.layoutManager = manager

        binding.recyclerviewFood.adapter = madapter

        //clickevnet

        madapter.setOnlistItemClickListener(object :OnListitemClickListner{
            override fun onCardClicked(pos: Int, food: Food) {
                Toast.makeText(applicationContext, food.name, Toast.LENGTH_SHORT).show()
            }

            override fun onImageClicked(view: View, pos: Int) {
                Toast.makeText(applicationContext, "image - $pos", Toast.LENGTH_SHORT).show()
            }

        })


        preparaData()
    }

    private fun preparaData() {

        userList.add(User(1,"umang",R.drawable.image1))
        userList.add(User(2,"sagar",R.drawable.image2))
        userList.add(User(3,"vipul",R.drawable.image3))
        userList.add(User(4,"amit",R.drawable.image4))
        userList.add(User(3,"vipul",R.drawable.image5))
        userList.add(User(4,"amit",R.drawable.image4))

        foodList.add(Food(1,"viral",R.drawable.image1))
        foodList.add(Food(2,"rajdeep",R.drawable.image2))
        foodList.add(Food(3,"dhiren",R.drawable.image3))
        foodList.add(Food(4,"montu",R.drawable.image4))

    }
}