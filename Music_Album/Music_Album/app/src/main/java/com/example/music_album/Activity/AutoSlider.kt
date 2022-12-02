package com.example.music_album.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.*
import com.example.music_album.Adoptors.Auto_Slider
import com.example.music_album.Adoptors.OnScrollListener
import com.example.music_album.Modal.Auto
import com.example.music_album.R
import com.example.music_album.databinding.ActivityAutoSliderBinding
import java.util.*
import kotlin.concurrent.thread

class AutoSlider : AppCompatActivity() {
    lateinit var binding: ActivityAutoSliderBinding
    private var autolist = mutableListOf<Auto>()

    private lateinit var Autoadoptor: Auto_Slider
        val speedScroll = 3000
    private lateinit var onScrollListener: OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAutoSliderBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adddata()

        Autoadoptor = Auto_Slider(this, autolist)
        var manager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.AutoView.layoutManager = manager
        binding.AutoView.adapter = Autoadoptor

      val handler = Handler()
        val runnable = object : Runnable {
            var count = 0
            var flag = true
            override fun run() {
                if(count==4){
                    count=0
                }
               if (count < Autoadoptor.itemCount) {
                  if (count == Autoadoptor.itemCount - 1) {
                       flag = false
                    }
                    else if (count == 0) {
                        flag = true
                    }
                    if (flag){
                        count++
                    } else{
                        count--
                    }
                    binding.AutoView.smoothScrollToPosition(count)
                    handler.postDelayed(this, speedScroll.toLong())
                }
            }
        }
        handler.postDelayed(runnable, speedScroll.toLong())


        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.AutoView)

//        Autoadoptor.getListSize()
//            .takeIf { it > 1 }
//            ?.apply {
//                onScrollListener = OnScrollListener(Autoadoptor.getListSize(), manager)
//                binding.AutoView.addOnScrollListener(onScrollListener)
//                binding.AutoView.scrollToPosition(Autoadoptor.getListSize())
//            }

        Autoadoptor.itemCount.takeIf {
            it>1
        }?.apply {
                onScrollListener=OnScrollListener(Autoadoptor.itemCount,manager,{
                    if(it==RecyclerView.SCROLL_STATE_DRAGGING)
                    {

                    }
                })
        }
        binding.AutoView.addOnScrollListener(onScrollListener)
        binding.AutoView.scrollToPosition(1)


    }


    private fun adddata() {
        autolist.add(Auto(1, R.drawable.firstp))
        autolist.add(Auto(2, R.drawable.second))
        autolist.add(Auto(3, R.drawable.three))
        autolist.add(Auto(4, R.drawable.fourth))
        autolist.add(Auto(5, R.drawable.five))

    }
}