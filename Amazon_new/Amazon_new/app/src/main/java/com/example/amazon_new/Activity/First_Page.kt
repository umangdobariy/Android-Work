package com.example.amazon_new.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazon_new.Adoptors.recyclelist2_Adoptor
import com.example.amazon_new.Adoptors.recyclelist_Adoptor
import com.example.amazon_new.Modal.*
import com.example.amazon_new.R
import com.example.amazon_new.databinding.ActivityFirstPageBinding

class First_Page : AppCompatActivity() {
    //parent layout
    lateinit var binding: ActivityFirstPageBinding
   var recyclelist = mutableListOf<recyclelist>()
   private lateinit var recAdoptor:recyclelist_Adoptor

   var recyclelist2 = mutableListOf<recyclelist2>()
    private lateinit var recAdoptor2:recyclelist2_Adoptor

   // val speedScroll = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //add data
        listData()

        //adoptor ma recyclelist pass karu
        recAdoptor = recyclelist_Adoptor(this,recyclelist)
        recAdoptor2 = recyclelist2_Adoptor(this,recyclelist2)

        //layout manger
       var manager = LinearLayoutManager(applicationContext)
        binding.ParecView.layoutManager = manager
        binding.ParecView.adapter = recAdoptor

        var manager2 = LinearLayoutManager(applicationContext)
        binding.ParecView2.layoutManager = manager2
        binding.ParecView2.adapter = recAdoptor2


    }

    private fun listData() {
        //value add kari
        recyclelist = mutableListOf()

        var horizontallist = mutableListOf<horizontal>()
        var autoscroll = mutableListOf<autoscroll>()

        recyclelist2 = mutableListOf()
        var thrdhorilist = mutableListOf<thred_horizontal>()
        var faltlist = mutableListOf<falt>()


        //parent in data adding
        recyclelist.add(recyclelist(horizontallist,autoscroll))
        recyclelist2.add(recyclelist2(thrdhorilist,faltlist))

        //horizontal list add
        horizontallist.add(horizontal(1,"Fashion",R.drawable.fashoin))
        horizontallist.add(horizontal(2,"Appliances",R.drawable.appliances))
        horizontallist.add(horizontal(3,"Beauty",R.drawable.beauty))
        horizontallist.add(horizontal(4,"Book",R.drawable.book))
        horizontallist.add(horizontal(5,"Deals",R.drawable.deals))
        horizontallist.add(horizontal(6,"Electronic",R.drawable.electronic))
        horizontallist.add(horizontal(7,"Fresh",R.drawable.fresh))
        horizontallist.add(horizontal(8,"Furnichers",R.drawable.furnicher))
        horizontallist.add(horizontal(9,"Home",R.drawable.home))
        horizontallist.add(horizontal(10,"MiniTv",R.drawable.minitv))
        horizontallist.add(horizontal(11,"Mobile",R.drawable.mobile))
        horizontallist.add(horizontal(12,"Movies",R.drawable.movies))
        horizontallist.add(horizontal(13,"Pharmarcy",R.drawable.pharmacy))
        horizontallist.add(horizontal(14,"Travel",R.drawable.train1))

        //auto scrolling
        autoscroll.apply {
            this.add(autoscroll(1, R.drawable.fp))
            this.add( autoscroll(2, R.drawable.second))
            this.add(autoscroll(3, R.drawable.three))
            this.add(autoscroll(4, R.drawable.fourth))
            this.add( autoscroll(5, R.drawable.five))
        }

        //recycle view 2
        thrdhorilist.add(thred_horizontal(1,R.drawable.am,"Amazon"))
        thrdhorilist.add(thred_horizontal(2,R.drawable.sec,"Amazon"))
        thrdhorilist.add(thred_horizontal(3,R.drawable.thrd,"Amazon"))
        thrdhorilist.add(thred_horizontal(4,R.drawable.forth,"Amazon"))
        thrdhorilist.add(thred_horizontal(5,R.drawable.fiv,"Amazon"))
        thrdhorilist.add(thred_horizontal(6,R.drawable.img,"Amazon"))
        thrdhorilist.add(thred_horizontal(7,R.drawable.sec,"Amazon"))

        //falt view
        faltlist.add(falt(1,R.drawable.debitcard,"Pay On Delivery"))
        faltlist.add(falt(2,R.drawable.pay,"Pay On Delivery"))
        faltlist.add(falt(3,R.drawable.delivery,"Free Delivery On First Order"))





    }
}