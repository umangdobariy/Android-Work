package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigationlec_21.Fragments.*
import com.example.demo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
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
                R.id.action_favorite -> {
                    addFragment("Favourite", FavoriteFragment())
                    true
                }
                R.id.action_search -> {
                    addFragment("Search", SearchFragment())
                    true
                }
                R.id.action_profile -> {
                    addFragment("Profile", ProfileFragment())
                    true
                }
                R.id.action_cart -> {
                    addFragment("Reels", CartFragment())
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