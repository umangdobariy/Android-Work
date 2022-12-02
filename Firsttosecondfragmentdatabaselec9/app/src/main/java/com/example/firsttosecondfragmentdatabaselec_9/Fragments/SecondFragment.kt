package com.example.firsttosecondfragmentdatabaselec_9.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firsttosecondfragmentdatabaselec_9.R
import com.example.firsttosecondfragmentdatabaselec_9.databinding.FragmentFristBinding
import com.example.firsttosecondfragmentdatabaselec_9.databinding.FragmentSecondBinding


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

        arguments?.let {


            var age = it.getInt("AGE")
            var name = it.getString("NAME")

            binding.tvAge.text = "AGE: $age"
            binding.tvName.text = "NAME : $name"
        }
    }

}