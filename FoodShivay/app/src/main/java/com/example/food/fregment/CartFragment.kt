package com.example.food.fregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.food.R
import com.example.food.databinding.CartFragmentBinding
import com.example.food.databinding.FragmentCartBinding
import com.example.food.modal.hor_list
import com.example.food.modal.sub_home
import java.lang.NumberFormatException

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    var count = 0
    var increment = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        arguments.let {
            var data = it?.getParcelable<sub_home>("data") as sub_home
            binding.pr.text = data.price
            binding.tital.text = data.Tit
            binding.ivImg.setImageResource(data.img)
        }
        var quntity = binding.number.text.toString()
        var tprice = binding.tvPrice.text.toString()
        binding.tvPrice.setText("0")
        binding.btnPlus.setOnClickListener {


            if (count < increment) {
                count++

                binding.number.setText(count.toString())
-

            } else {
                //count--
                //binding.number.setText(count)
            }


            //  binding.number.setText(count++)

            //count++
            Toast.makeText(requireActivity(), "pluse", Toast.LENGTH_SHORT).show()
        }
        binding.btnMinese.setOnClickListener {
            count--
            if (count < 0) {
                count = 0
            }
            binding.number.setText(count.toString())
            Toast.makeText(requireActivity(), "mines", Toast.LENGTH_SHORT).show()


        }

        return binding.root

    }

}