package com.example.customgridviewlec_16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customgridviewlec_16.Adapter.CustomGridAdapter
import com.example.customgridviewlec_16.Modal.Cloth
import com.example.customgridviewlec_16.databinding.ActivityGridviewBinding

class GridviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridviewBinding

    private var  clothList = mutableListOf<Cloth>()

    private lateinit var adapter: CustomGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareData()

        adapter = CustomGridAdapter(this,clothList)

        binding.gridView.adapter = adapter
    }

    private fun prepareData() {
        clothList.add(Cloth(1,"t-shirt",252.25f,"M","Blue",R.drawable.image1))
        clothList.add(Cloth(2,"shirt",322.23f,"L","Black",R.drawable.image2))
        clothList.add(Cloth(3,"saree",899.99f,"L","pink",R.drawable.image3))
        clothList.add(Cloth(4,"dress",799.97f,"M","brown",R.drawable.image4))
        clothList.add(Cloth(5,"kurti",599.98f,"L","light brown",R.drawable.image5))

    }
}