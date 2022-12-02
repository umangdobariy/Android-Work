package com.example.navigationdrawerlec_52.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.navigationdrawerlec_52.databinding.ItemOnboardingLayoutBinding
import com.example.navigationdrawerlec_52.model.Item


class CustompagerAdapter(var context:Context,var itemList: MutableList<Item>): PagerAdapter() {

    private lateinit var binding: ItemOnboardingLayoutBinding

    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var item = itemList[position]
        binding = ItemOnboardingLayoutBinding.inflate(LayoutInflater.from(context),container,false)

        binding.udTitle.text = item.title
        binding.udDesc.text = item.desc
        binding.ivThumbnail.setImageResource(item.image)

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}