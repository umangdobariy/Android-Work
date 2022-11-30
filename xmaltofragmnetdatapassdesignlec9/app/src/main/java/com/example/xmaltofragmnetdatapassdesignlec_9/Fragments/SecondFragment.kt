package com.example.xmaltofragmnetdatapassdesignlec_9.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xmaltofragmnetdatapassdesignlec_9.R
import com.example.xmaltofragmnetdatapassdesignlec_9.databinding.FragmentFristBinding
import com.example.xmaltofragmnetdatapassdesignlec_9.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun updatesendData(name:String,age:Int)
    {
        binding.tvName.text = "name:$name"
        binding.tvAge.text = "age:$age"
    }



}