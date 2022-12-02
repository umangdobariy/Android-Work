package com.example.food.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.food.modal.Item
import com.example.food.R
import com.example.food.adaptar.on_boding_adaptor
import com.example.food.databinding.ActivityOnBodingBinding

class OnBodingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBodingBinding
    private var itemlist = mutableListOf<Item>()
    private var currentIndex = 0
    private lateinit var mAdoptor :on_boding_adaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBodingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set data in method
        setData()
         //btn_next nu clicke event hendling
                binding.btnNext.setOnClickListener {
                    if(currentIndex!=mAdoptor.count-1){
                        currentIndex++
                        binding.viewPager.currentItem = currentIndex
                    }else{
                        //finish no clicke evnet is here to handaling it
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                        Toast.makeText(applicationContext, "Navigrat to secand activity ", Toast.LENGTH_SHORT).show()
                    }

                }

        //btn_start nu clicke event
        binding.btnStart.setOnClickListener {
            //navigration to home activity
            startActivity(Intent(this,HomeActivity::class.java))
        }
        //btn_skip
        binding.btnSkip.setOnClickListener {
            currentIndex = 2
            binding.viewPager.currentItem = currentIndex
        }


        //passing data adoptor tousing constructor
        mAdoptor = on_boding_adaptor(applicationContext,itemlist)
        binding.viewPager.adapter = mAdoptor

        updateIndicator(currentIndex)

        binding.viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                currentIndex = position
                if (currentIndex==mAdoptor.count-1){
                    binding.btnNext.text= "FINISH"
                    //skip remove
                    binding.btnSkip.text = ""
                }else{
                    binding.btnNext.text = "NEXT"
                    binding.btnSkip.text = "Skip"

                }



                updateIndicator(currentIndex)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


    }

    private fun updateIndicator(currentIndex: Int) {
        //anathi dark aglna view remove thai jase
        binding.linear.removeAllViews()


        //set layout in margin Top,left,Right,Bottam
        var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(8,0,0,0)

        for (i in itemlist.indices){
            //view in set image
            var view = ImageView(applicationContext)
            view.layoutParams = lp
            if(i==currentIndex){
                view.setImageResource(R.drawable.activted_indicator)

            }else{
                view.setImageResource(R.drawable.inactivted_indicator)
            }
            //layout ma Dainamical Image view adding
            binding.linear.addView(view)

        }


    }

    private fun setData() {
        itemlist.add(Item(1,"Order For Food",R.drawable.f1,"Place Order For What You Want From Any Restarunt Of Your Choice"))
        itemlist.add(Item(2,"Swift Delivery",R.drawable.s1,"Recieve Your Order In less than 1Hour or Pick Specific Delivery Time"))
        itemlist.add(Item(3,"Tracking Order",R.drawable.t1,"RealTime - Tracking will Keep You Posting About Order Progress"))

    }
}