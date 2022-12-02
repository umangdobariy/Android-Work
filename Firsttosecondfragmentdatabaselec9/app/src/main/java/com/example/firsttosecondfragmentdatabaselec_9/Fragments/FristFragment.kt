package com.example.firsttosecondfragmentdatabaselec_9.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firsttosecondfragmentdatabaselec_9.R
import com.example.firsttosecondfragmentdatabaselec_9.databinding.FragmentFristBinding


class FristFragment : Fragment() {

    lateinit var binding: FragmentFristBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentFristBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.udButton.setOnClickListener {

            var name = binding.udName.text.toString().trim()
            var age = binding.udAge.text.toString().trim()

            var bundle = Bundle()
            bundle.putInt("AGE",age.toInt())
            bundle.putString("NAME",name)

            var fragment = SecondFragment()
            fragment.arguments = bundle

            var manager = requireActivity().supportFragmentManager
            var transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
//            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}