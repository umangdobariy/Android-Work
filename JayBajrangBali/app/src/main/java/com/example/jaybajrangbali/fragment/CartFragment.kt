package com.example.jaybajrangbali.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jaybajrangbali.R
import com.example.jaybajrangbali.adapter.Cart1Adapter
import com.example.jaybajrangbali.databinding.FragmentCartBinding
import com.example.jaybajrangbali.model.Profile
import com.example.jaybajrangbali.model.Profile1

class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentCartBinding.inflate(inflater,container,false)
        return binding.root

    }

}