package com.example.food.fregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.food.R
import com.example.food.databinding.FragmentHomeBinding
import com.example.food.databinding.FragmentHoriDetailsBinding
import com.example.food.modal.sub_home

class hori_detailsFragment : Fragment() {
    lateinit var binding: FragmentHoriDetailsBinding

    lateinit var data :sub_home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHoriDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var bundle=Bundle()

        arguments.let {
           data = it?.getParcelable<sub_home>("bundle") as sub_home

            binding.name.text=data.Tit
            binding.price.text=data.price
            binding.ivImg.setImageResource(data.img)
        }



        binding.btnCart.setOnClickListener {

            var bundle = Bundle()
            bundle.putParcelable("data",data)

            var fragment: Fragment = CartFragment()
            fragment.arguments = bundle
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.cart_container, fragment)
                .commit()
        }

    }
}