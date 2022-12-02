package com.example.jaybajrangbali.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.jaybajrangbali.R
import com.example.jaybajrangbali.databinding.ActivityHomeAcitivityBinding
import com.example.jaybajrangbali.fragment.CartFragment
import com.example.jaybajrangbali.fragment.CategoryFragment
import com.example.jaybajrangbali.fragment.HomeFragment
import com.example.jaybajrangbali.fragment.ProfileFragment

class HomeAcitivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolBar.title = "Home"
        setSupportActionBar(binding.toolBar)

        addFragment("Home", HomeFragment())


        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    addFragment("Home", HomeFragment())
                    true
                }
                R.id.action_category -> {
                    addFragment("Category", CategoryFragment())
                    true
                }
                R.id.action_cart -> {
                    addFragment("Cart", CartFragment())
                    true
                }
                R.id.action_profile -> {
                addFragment("Profile", ProfileFragment())
                true
            }
                else -> false
            }
        }
    }


    private fun addFragment(title: String, fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment, title)
            addToBackStack(null)
            commit()
        }
        binding.toolBar.title = title
    }
}