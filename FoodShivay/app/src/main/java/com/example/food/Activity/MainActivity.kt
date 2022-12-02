package com.example.food.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food.adaptar.viewpager_adoptor
import com.example.food.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setuptablayout()
        setupviewpager()
    }


    private fun setupviewpager() {
       binding.pager.apply {
           adapter = viewpager_adoptor(supportFragmentManager,binding.tabLayout.tabCount)
           addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

       }
    }

    private fun setuptablayout() {
        binding.tabLayout.apply {
            addTab(this.newTab().setText("SignIn"))
            addTab(this.newTab().setText("SignUp"))

            addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        binding.pager.currentItem = it
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }


            })

        }
    }
}