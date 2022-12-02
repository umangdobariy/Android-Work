package com.example.whatsapp

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.view_pager)
        viewPager2.adapter = PagerAdapter(this)

        TabLayoutMediator(tabLayout,viewPager2){tab,index ->
            tab.text = when(index){
                0 -> {"Image"}
                1 -> {"Status"}
                2 -> {"Saved"}

                else -> {throw Resources.NotFoundException("Position Not Found")}
            }
        }.attach()
    }
}