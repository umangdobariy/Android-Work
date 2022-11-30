package com.example.onboardingscreenlec_2223.Acitivites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.mahadev.shredpref.PrefManagr

import com.example.onboardingscreenlec_2223.R
import com.example.onboardingscreenlec_2223.adpter.CustompagerAdapter
import com.example.onboardingscreenlec_2223.databinding.ActivityIntroBinding
import com.example.onboardingscreenlec_2223.modal.item

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private var itemList = mutableListOf<item>()
    private lateinit var adapter: CustompagerAdapter
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareData()

        //pass data to adapter using adapterview
        adapter = CustompagerAdapter(this,itemList)

        binding.viewPager.adapter = adapter

        updateIndicator(currentIndex)

        binding.btnNext.setOnClickListener {
            if (currentIndex<adapter.count){
            currentIndex++
            binding.viewPager.currentItem = currentIndex
        }else{
            // navigate to login activity
            updateIntroStatus()
            startActivity(Intent(applicationContext,LoginActivity::class.java))
             }
        }

            binding.btnSkip.setOnClickListener {
                updateIntroStatus()
            }

        binding.viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                   currentIndex = position

                   if (currentIndex == adapter.count-1){
                       // last page
                       binding.btnNext.text = "Finish"
                       binding.btnSkip.visibility = View.GONE
                       binding.btnNext.icon = null


                   }else{
                       binding.btnNext.text = "Next"
                       binding.btnSkip.visibility = View.VISIBLE
                       binding.btnNext.icon = ContextCompat.getDrawable(applicationContext,R.drawable.ic_baseline_arrow_forward_24)
                   }

                    updateIndicator(currentIndex)

                   //Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show

            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun updateIntroStatus() {
        var prefManagr = PrefManagr(this)
        prefManagr.setIntrostatus(true)
        startActivity(Intent(applicationContext,LoginActivity::class.java))
    }

    private fun updateIndicator(index: Int) {

        binding.indicatorDots.removeAllViews()

        var indicators = arrayOfNulls<ImageView>(3)

        for (i in indicators.indices)
        {
            indicators[i] = ImageView(this)

            if (i==index){
                indicators[i]?.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_indicator))
            }else{
                indicators[i]?.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive_indicator))
            }

            var param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            param.setMargins(16,0,0,0)

            binding.indicatorDots.addView(indicators[i],param)
        }
    }

    private fun prepareData() {

        itemList.add(item(1,"Fresh Food","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eu auctor mauris. Vestibulum vestibulum, purus nec imperdiet accumsan",R.drawable.onboarding1))
        itemList.add(item(2,"Fast Delivery","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eu auctor mauris. Vestibulum vestibulum, purus nec imperdiet accumsan",R.drawable.onboarding2))
        itemList.add(item(3,"Easy Payment","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eu auctor mauris. Vestibulum vestibulum, purus nec imperdiet accumsan",R.drawable.onboarding3))

    }
}