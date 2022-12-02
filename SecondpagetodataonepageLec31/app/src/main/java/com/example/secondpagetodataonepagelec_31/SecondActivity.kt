package com.example.secondpagetodataonepagelec_31

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secondpagetodataonepagelec_31.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var message = intent.getStringExtra("MSG")

        message?.let {
            binding.tvResult.text = it
        }

        binding.btnBack.setOnClickListener {

            var intent = Intent()
            intent.putExtra("RES","Umang")
            setResult(RESULT_OK,intent)
            onBackPressed()
        }
    }
}