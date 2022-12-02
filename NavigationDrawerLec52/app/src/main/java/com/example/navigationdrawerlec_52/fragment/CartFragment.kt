package com.example.navigationdrawerlec_52.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationdrawerlec_52.R
import com.example.navigationdrawerlec_52.databinding.FragmentCartBinding
import com.example.navigationdrawerlec_52.databinding.FragmentCategoryBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root

    }

}