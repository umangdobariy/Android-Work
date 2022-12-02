package com.example.adapterlec_15

import android.content.AbstractThreadedSyncAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.adapterlec_15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var languages = arrayOf("java","kotlin","php","dart","c++","node js")
    lateinit var  mSpinnerAdapter: ArrayAdapter<String>
    lateinit var mAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,languages)
        mSpinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,languages)
        binding.listItem.adapter = mAdapter
        binding.spinner.adapter = mSpinnerAdapter
        binding.gridView.adapter = mAdapter

        binding.gridView.setOnItemClickListener { adapterView, view, posi, l ->

            var langs = languages[posi]
            Toast.makeText(this, "$langs", Toast.LENGTH_SHORT).show()
        }



        binding.listItem.setOnItemClickListener { adapterView, view, pos, l ->

            var lang = languages[pos]
            Toast.makeText(this, "$lang", Toast.LENGTH_SHORT).show()
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {

                var lang = languages[pos]
                Toast.makeText(applicationContext, "$lang", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }




    }
}