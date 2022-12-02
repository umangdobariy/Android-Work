package com.example.internalstoragelec_29

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.internalstoragelec_29.databinding.ActivityMainBinding
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val FILE_NAME = "tops-student.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.udSave.setOnClickListener {

            var message = binding.udMessage.text.toString().trim()
            saveData(message)

        }

        binding.udRead.setOnClickListener {

            readData()
        }
    }

    private fun readData() {

        try {

            val fin = openFileInput(FILE_NAME)
            Log.d("RESPONSE", "Size:${fin.available()} ")
            fin.available()
            var array:ByteArray = ByteArray(fin.available())
            fin.read(array)

            var message = String(array)

            binding.udResult.text = message

            fin.close()

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun saveData(message: String) {

        try {
            val fout:FileOutputStream = openFileOutput(FILE_NAME, MODE_APPEND)
            fout.write(message.toByteArray())
            fout.close()

            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()


        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}