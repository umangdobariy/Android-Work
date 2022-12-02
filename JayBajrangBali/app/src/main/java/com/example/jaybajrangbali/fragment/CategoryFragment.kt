package com.example.jaybajrangbali.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jaybajrangbali.R
import com.example.jaybajrangbali.adapter.FilpAdapter
import com.example.jaybajrangbali.databinding.FragmentCategoryBinding
import com.example.jaybajrangbali.model.Categoryfilp


class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private var itemList = mutableListOf<Categoryfilp>()
    private lateinit var adapter:FilpAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCategoryBinding.inflate(inflater,container,false)


        prepareData()

        adapter = FilpAdapter(requireContext() as Activity,itemList)


         var layoutManager=GridLayoutManager(requireContext(),3)
        binding.uvRecyclerview.layoutManager=layoutManager
        binding.uvRecyclerview.adapter=adapter




        return  binding.root
    }

    private fun prepareData() {

        itemList.add(Categoryfilp(1,"Cloth",R.drawable.image1))
        itemList.add(Categoryfilp(2,"Mobile",R.drawable.image2))
        itemList.add(Categoryfilp(3,"Sports",R.drawable.image3))
        itemList.add(Categoryfilp(4,"Bikes",R.drawable.image4))
        itemList.add(Categoryfilp(5,"Medicines",R.drawable.image5))
        itemList.add(Categoryfilp(1,"Home",R.drawable.image1))
        itemList.add(Categoryfilp(2,"Toys",R.drawable.image2))
        itemList.add(Categoryfilp(3,"Hotels",R.drawable.image3))
        itemList.add(Categoryfilp(4,"Appliances",R.drawable.image4))
        itemList.add(Categoryfilp(5,"Furniture",R.drawable.image5))




    }

}