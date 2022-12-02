package com.example.secondpagetodataonepagelec_31

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.secondpagetodataonepagelec_31.databinding.ActivityFristBinding
import com.example.secondpagetodataonepagelec_31.databinding.ActivityMainBinding

class FristActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFristBinding
    private val REQ_CODE = 100

    // Receiver
    var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            it.data?.let {
                var message  = it.getStringExtra("RES")
                binding.tvResult.text = message
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFristBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNext.setOnClickListener {

            var message = binding.etMessage.text.toString().trim()

            // Caller

            // passing data from first activity to second
            var intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("MSG",message)
//            startActivity(intent)
//            startActivityForResult(intent,REQ_CODE)
            result.launch(intent)
        }
    }

    // Receiver

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE && resultCode == RESULT_OK){

            data?.let {

                var message  = it.getStringExtra("RES")
                binding.tvResult.text = message
            }
        }
    }
}