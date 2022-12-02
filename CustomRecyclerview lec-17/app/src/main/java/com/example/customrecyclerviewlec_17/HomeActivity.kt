package com.example.customrecyclerviewlec_17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customrecyclerviewlec_17.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {


   private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        let{
            var title=intent.getStringExtra("title")
            binding.tvTitle.text=title.toString()
            var type=intent.getStringExtra("type")
            binding.tvColor.text = type.toString()

        }

        val bundle: Bundle? =intent.extras

        val resId = bundle!!.getInt("img")
        binding.ivMontu.setImageResource(resId)

        var rating = bundle!!.getFloat("rating")
        binding.tvRating.rating = rating.toString().toFloat()

      //  var mrp = bundle!!.getInt("mrp")
     //   binding.tvMrp.text = mrp.toString()

    }
}