package com.example.food.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.food.modal.Item
import com.example.food.databinding.OnBodingLayoutBinding

class on_boding_adaptor(var context: Context, var itemlist :MutableList<Item>):PagerAdapter() {

    lateinit var binding: OnBodingLayoutBinding
    override fun getCount(): Int {
        return itemlist.size

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view ==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        binding = OnBodingLayoutBinding.inflate(LayoutInflater.from(context),container,false)

        var item =itemlist[position]

        binding.ivImg.setImageResource(item.Img)
        binding.tvDes.text = item.Des
        binding.tvTital.text = item.Tital

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)

    }
}