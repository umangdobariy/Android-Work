package com.example.customgridviewlec_16.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.customgridviewlec_16.Modal.Cloth
import com.example.customgridviewlec_16.databinding.ColthItemListBinding

class CustomGridAdapter(var context: Context, var  clotlist:MutableList<Cloth>):BaseAdapter() {

    lateinit var binding: ColthItemListBinding

    override fun getCount(): Int {
        return clotlist.size
    }

    override fun getItem(pos: Int): Any {
        return clotlist[pos]
    }

    override fun getItemId(pos: Int): Long {
        return clotlist[pos].id.toLong()
    }

    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View {
        binding = ColthItemListBinding.inflate(LayoutInflater.from(context),parent,false)

        var cloth = clotlist[pos]
        binding.udName.text = cloth.name
        binding.udPrice.text = "${cloth.mrp}"
        binding.udSize.text = cloth.size
        binding.udColor.text = cloth.color
        binding.udImage.setImageResource(cloth.image)

        return binding.root
    }
}