package com.example.xmaltofragmnetdatapassdesignlec_9.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xmaltofragmnetdatapassdesignlec_9.R
import com.example.xmaltofragmnetdatapassdesignlec_9.databinding.FragmentFristBinding


class FristFragment : Fragment() {

    lateinit var binding: FragmentFristBinding
    lateinit var Communicator:Communicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFristBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Communicator = context as Communicator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {

            var name = binding.etName.text.toString().trim()
            var age = binding.etAge.text.toString().trim()

            Communicator.sendData(name,age.toInt())
        }
    }

}