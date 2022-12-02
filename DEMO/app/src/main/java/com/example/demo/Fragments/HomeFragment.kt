package com.example.bottomnavigationlec_21.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.demo.R
import com.example.demo.adpter.CustomVertical
import com.example.demo.databinding.ActivityHomeBinding
import com.example.demo.databinding.FragmentHomeBinding
import com.example.demo.modal.Food


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var itemList = mutableListOf<Food>()
    private lateinit var adapter:CustomVertical

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

        preparaData()


    }

    private fun preparaData() {
        itemList.add(Food(1,"Burger","Beef Burger",569.25f,2.5f,R.drawable.image1,2002))
        itemList.add(Food(2,"Pizza","Garlic Pizza",122.23f,3.5f,R.drawable.image2,2003))
        itemList.add(Food(3,"Pani puri","Limi puri",70.25F,1.5f,R.drawable.image3,2004))
        itemList.add(Food(4,"Sandwich","Gril Sandwich",133.25f,4.2f,R.drawable.image4,2005))
        itemList.add(Food(5,"Hotdog","Corn dog",252.23f,4.5f,R.drawable.image5,2006))

    }


}