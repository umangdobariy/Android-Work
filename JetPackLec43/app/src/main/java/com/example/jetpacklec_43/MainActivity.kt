package com.example.jetpacklec_43

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacklec_43.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        mainViewModel = ViewModelProvider(this,MainViewModelFactory(10)).get(MainViewModel::class.java)
        binding.mainModel = mainViewModel
        binding.lifecycleOwner = this

//        mainViewModel.counterLiveData.observe(this, Observer {
//            binding.tvCounter.text = "$it"
//        })



        binding.btnIncrement.setOnClickListener {
            mainViewModel.increment()

        }

        binding.btnDecrement.setOnClickListener {
            mainViewModel.decrement()

        }

    }

}