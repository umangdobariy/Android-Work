package com.example.customlistviewlec_16.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.customlistviewlec_16.HomeActivity
import com.example.customlistviewlec_16.databinding.FoodItemListBinding
import com.example.customlistviewlec_16.modal.Food


class CustomListAdapter(var context:Context,var foodlist:MutableList<Food>):BaseAdapter() {

    lateinit var binding: FoodItemListBinding

    override fun getCount(): Int {
        return foodlist.size
    }

    override fun getItem(pos: Int): Any {
        return foodlist[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View {

        binding = FoodItemListBinding.inflate(LayoutInflater.from(context),parent,false)

        var food = foodlist[pos]
        binding.udName.text = food.Name
        binding.udType.text = food.type
        binding.udPrice.text = "${food.Price}"
        binding.udYear.text = food.year.toString()
        binding.udRating.rating = food.rating
        binding.udThumbnail.setImageResource(food.image)


        var kdname = foodlist.get(pos).Name
        var kdtype = foodlist.get(pos).type
        var kdprice = foodlist.get(pos).Price
        var kdyear = foodlist.get(pos).year
        var kdrating = foodlist.get(pos).rating
        var kdthumbnail = foodlist.get(pos).image

        binding.root.setOnClickListener {
            var intent = Intent(context,HomeActivity::class.java)
            intent.putExtra("name",kdname)
            intent.putExtra("type",kdtype)
            intent.putExtra("price",kdprice)
            intent.putExtra("year",kdyear)
            intent.putExtra("rating",kdrating)
            intent.putExtra("image",kdthumbnail)
            context.startActivity(intent)
        }

        return binding.root
    }
}