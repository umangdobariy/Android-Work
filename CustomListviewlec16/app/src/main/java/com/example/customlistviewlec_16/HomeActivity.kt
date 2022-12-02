package com.example.customlistviewlec_16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customlistviewlec_16.databinding.ActivityCustomlistBinding
import com.example.customlistviewlec_16.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        let {
          //  var image = intent.getStringExtra("image")
            var name = intent.getStringExtra("name")
            var type = intent.getStringExtra("type")
            var price = intent.getStringExtra("price")
           // var year = intent.getStringExtra("year")
           // var rating = intent.getStringExtra("rating")


            binding.udName.text = name.toString()
            binding.udType.text = type.toString()
          //  binding.udPrice.text= price.toString()
           // binding.udYear.text = year.toString()
           // binding.udRating.rating= rating.toString().toFloat()

        }

        val bundle:Bundle? = intent.extras
        val resid:Int = bundle!!.getInt("image")
        binding.udThumbnail.setImageResource(resid)

        var price=bundle!!.getFloat("price")
        binding.udPrice.text= price.toString()

        var rating=bundle!!.getFloat("rating")
        binding.udRating.rating= rating.toString().toFloat()

        var year=bundle!!.getInt("year")
        binding.udYear.text = year.toString()

    }


}