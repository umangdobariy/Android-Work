package com.example.materialdesignlec_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.materialdesignlec_20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter:ArrayAdapter<String>
    var cities = arrayOf("surat","jamnagar","Rajkot","Morbi","Valsad","Bhavnagar","Vartej","Ramdhari")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,cities)
        binding.autoTvCity.setAdapter(adapter)
    }
}