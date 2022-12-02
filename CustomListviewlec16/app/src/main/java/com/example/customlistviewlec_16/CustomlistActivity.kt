package com.example.customlistviewlec_16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customlistviewlec_16.adapter.CustomListAdapter
import com.example.customlistviewlec_16.databinding.ActivityCustomlistBinding

import com.example.customlistviewlec_16.modal.Food

class CustomlistActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCustomlistBinding

    private var foodList = mutableListOf<Food>()
    private lateinit var adapter: CustomListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareData()
        // pass data to adapter using constructor
        adapter = CustomListAdapter(this,foodList)
        binding.listItem.adapter = adapter

    }

    private fun prepareData() {

        foodList.add(Food(1,"Burger","Beef Burger",252.25f,3.5f,R.drawable.image1,2002))
        foodList.add(Food(1,"Pizza","Margarita Pizza",150.34f,4.5f,R.drawable.image2,2003))
        foodList.add(Food(1,"Pani Puri","Lmli Puri",50.25f,2.5f,R.drawable.image3,2004))
        foodList.add(Food(1,"Sandwich","Grill Sandwich",170.75f,1.5f,R.drawable.image4,2005))
        foodList.add(Food(1,"Hotdog","Corn dog",80.35f,3.5f,R.drawable.image5,2006))


        foodList.add(Food(1,"Sandwich","Grill Sandwich",170.75f,1.5f,R.drawable.image4,2005))
        foodList.add(Food(1,"Hotdog1234","Corn dog",80.35f,2.5f,R.drawable.image5,2006))
        foodList.add(Food(1,"Pizza","Margarita Pizza",150.34f,4.5f,R.drawable.image2,2008))

        foodList.add(Food(1,"Burger1234","Beef Burger",252.25f,3.5f,R.drawable.image1,2005))

    }
}