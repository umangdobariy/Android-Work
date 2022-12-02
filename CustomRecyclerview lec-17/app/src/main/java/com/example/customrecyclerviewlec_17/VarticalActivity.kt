package com.example.customrecyclerviewlec_17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customrecyclerviewlec_17.databinding.ActivityVarticalBinding

class VarticalActivity : AppCompatActivity() {

    lateinit var binding:ActivityVarticalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_vartical)
       binding=ActivityVarticalBinding.inflate(layoutInflater)
       setContentView(binding.root)

        initview()
    }


        fun initview(){
            let{
                var title=intent.getStringExtra("title")
                binding.tvTitle.text=title.toString()
                var type=intent.getStringExtra("type")
                binding.tvType.text = type.toString()

            }

            val bundle: Bundle? =intent.extras

            val resId = bundle!!.getInt("img")
            binding.ivMontu.setImageResource(resId)

            var rating = bundle!!.getFloat("rating")
            binding.tvRating.rating = rating.toString().toFloat()

              var mrp = bundle!!.getFloat("Price")
               binding.tvPrice.text = mrp.toString()


            var year = bundle!!.getInt("year")
            binding.tvYear.text = year.toString()

        }
    }
