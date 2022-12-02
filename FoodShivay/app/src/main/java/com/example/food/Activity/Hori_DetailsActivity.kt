package com.example.food.Activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.food.R
import com.example.food.databinding.ActivityHoriDetailsBinding

import com.example.food.fregment.CartFragment
import com.example.food.fregment.hori_detailsFragment
import com.example.food.modal.sub_home

class Hori_DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityHoriDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoriDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var getdata = intent.getParcelableExtra<sub_home>("subhome")

        Log.d("TAG", "onCreate: " + getdata)

        var bundle = Bundle()
        bundle.putParcelable("bundle", getdata)

        var fragment: Fragment = hori_detailsFragment()
       // var fregment = CartFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.cart_container, fragment)
            .commit()


//            bundal.putParcelable("obj",obj)
//            var fregment = CartFragment()
//             fregment.arguments = bundal
//
//                    supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fregment)
//                    .commit()


    }
}