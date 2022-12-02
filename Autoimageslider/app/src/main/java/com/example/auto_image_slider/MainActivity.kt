package com.example.auto_image_slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.auto_image_slider.databinding.ActivityMainBinding
import com.example.auto_image_slider.modal.list
import com.example.auto_image_slider.modal.sliderImageAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var itemList = mutableListOf<list>()
    lateinit var adapter: sliderImageAdapter

    companion object{
        private var currentPage=0
        private var Num_pages=0
    }

    var imageList = listOf(R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Autoslider()
    }

    private fun Autoslider() {

        adapter = sliderImageAdapter(this,imageList)
        binding.viewAutoscroll.adapter=adapter

        //var density=resources.displayMetrics.density

        Num_pages=imageList.size

        var handler=Handler()
        var update= Runnable {
            if (currentPage == Num_pages){
                currentPage=0
            }
            binding.viewAutoscroll.setCurrentItem(currentPage++,true)
        }

        var swimTimer=Timer()
        swimTimer.schedule(object :TimerTask(){
            override fun run() {
                handler.post(update)
            }

        },1000,2000)
    }
}