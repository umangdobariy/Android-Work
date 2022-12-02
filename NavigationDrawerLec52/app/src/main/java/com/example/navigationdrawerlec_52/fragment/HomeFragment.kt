package com.example.navigationdrawerlec_52.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jaybajrangbali.adapter.CategoryAdapter
import com.example.jaybajrangbali.adapter.CategoryAdapter2
import com.example.jaybajrangbali.adapter.CategoryAdapter3
import com.example.jaybajrangbali.model.*
import com.example.navigationdrawerlec_52.R
import com.example.navigationdrawerlec_52.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    lateinit var categoryList: MutableList<Category>
    lateinit var parentAdapter: CategoryAdapter

    lateinit var categorList2:MutableList<Category2>
    lateinit var parentAdapter2: CategoryAdapter2

    lateinit var categorList3:MutableList<Category3>
    lateinit var parentAdapter3: CategoryAdapter3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =   FragmentHomeBinding.inflate(inflater,container,false)

        prepareData()

        parentAdapter = CategoryAdapter(requireContext(),categoryList)
        binding.parentView.layoutManager = LinearLayoutManager(requireContext())
        binding.parentView.adapter = parentAdapter

        //click
        parentAdapter.setonItemClickListner(object : CategoryAdapter.ItemClickListener{
             override fun filterAllClicked(id: Int, position: Int, title: String) {
                Toast.makeText(requireContext(), "$title : $position", Toast.LENGTH_SHORT).show()
            }

            override fun onChildItemClicked(view: View, subcategory: Subcategory) {
                Toast.makeText(requireContext(), "${subcategory.name} : ${subcategory.id}", Toast.LENGTH_SHORT).show()
            }

        })

        parentAdapter2 = CategoryAdapter2(requireContext(),categorList2)
        binding.parentView2.layoutManager = LinearLayoutManager(requireContext())
        binding.parentView2.adapter = parentAdapter2

        parentAdapter3 = CategoryAdapter3(requireContext(),categorList3)
        binding.parentView3.layoutManager = LinearLayoutManager(requireContext())
        binding.parentView3.adapter = parentAdapter3

        return binding.root
    }

    private fun prepareData() {

        var subList = mutableListOf<Subcategory>()


        subList.add(Subcategory(1,"Burger", R.drawable.image1,2.5f))
        subList.add(Subcategory(2,"Pizza",R.drawable.image2,3.5f))
        subList.add(Subcategory(3,"Pani puri",R.drawable.image3,4.4f))
        subList.add(Subcategory(4,"Sandwich",R.drawable.image4,2.8f))
        subList.add(Subcategory(5,"Hotdog",R.drawable.image5,1.5f))

        categoryList = mutableListOf()
        categoryList.add(Category(1,"Dashboard",subList))


        var subList2 = mutableListOf<Subcategory2>()

        subList2.add(Subcategory2(1,"Pow Bhaji",R.drawable.image1,2.3f))
        subList2.add(Subcategory2(2,"Meghi",R.drawable.image2,3.5f))
        subList2.add(Subcategory2(3,"Panjabi",R.drawable.image3,1.3f))
        subList2.add(Subcategory2(4,"Kajukatri",R.drawable.image4,4.5f))

        categorList2 = mutableListOf()
        categorList2.add(Category2(2,"Trending this week",subList2))



        var subList3 = mutableListOf<Subcategory3>()
        subList3.add(Subcategory3(1,"Pow Bhaji",R.drawable.image1,2.3f))
        subList3.add(Subcategory3(2,"Meghi",R.drawable.image2,3.5f))
        subList3.add(Subcategory3(3,"Panjabi",R.drawable.image3,1.3f))
        subList3.add(Subcategory3(4,"Kajukatri",R.drawable.image4,4.5f))

        categorList3 = mutableListOf()
        categorList3.add(Category3(3,"Trending this week",subList3))



    }


}