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
import com.example.jaybajrangbali.databinding.FragmentProfileBinding
import com.example.jaybajrangbali.model.Profile
import com.example.jaybajrangbali.model.Profile1


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    //private var itemList = mutableListOf<Profile>()
    private var itemList1 = mutableListOf<Profile1>()
    lateinit var parentAdapter: Cart1Adapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        preparaData()

        parentAdapter = Cart1Adapter(requireContext() as Activity, itemList1)
        binding.parentView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.parentView.adapter = parentAdapter


        return binding.root
    }


    private fun preparaData() {


        var itemList = mutableListOf<Profile>()



        itemList.add(
            Profile(
                1,
                R.drawable.ic_baseline_account_box_24,
                "FlipKart Plus",
                R.drawable.ic_baseline_keyboard_arrow_right_24
            )
        )
        itemList.add(
            Profile(
                2,
                R.drawable.ic_baseline_account_box_24,
                "Edit Profile",
                R.drawable.ic_baseline_keyboard_arrow_right_24
            )
        )
        itemList.add(
            Profile(
                3,
                R.drawable.ic_baseline_account_box_24,
                "Saved Address",
                R.drawable.ic_baseline_keyboard_arrow_right_24
            )
        )
        itemList.add(
            Profile(
                4,
                R.drawable.ic_baseline_account_box_24,
                "Select Language",
                R.drawable.ic_baseline_keyboard_arrow_right_24
            )
        )
        itemList.add(
            Profile(
                5,
                R.drawable.ic_baseline_account_box_24,
                "Notification Settings",
                R.drawable.ic_baseline_keyboard_arrow_right_24
            )
        )

        itemList1 = mutableListOf()
        itemList1.add(Profile1(1, "Accout Settings", itemList))

    }

}