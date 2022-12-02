package com.example.camerapicimage

import android.Manifest
import android.content.DialogInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.example.camerapicimage.databinding.ActivityImageBinding
import java.io.File

class Image_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    //Receiver

    val galleryContract = registerForActivityResult(ActivityResultContracts.GetContent()){
        it.let {
            binding.ivProfile.setImageURI(it)
        }
    }

        lateinit var imageUri:Uri

        val cameraContract = registerForActivityResult(ActivityResultContracts.TakePicture()){
            if (it){
                binding.ivProfile.setImageURI(imageUri)


            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageUri = createImageUri()!!

        binding.ivProfile.setOnClickListener {
            showOptionDialog()

        }

    }

    private fun showOptionDialog() {
        val items = arrayOf("From Gallery","From Camera")

        AlertDialog.Builder(this)
            .setTitle("Select option")
            .setItems(items, DialogInterface.OnClickListener { dialogInterface, i ->

                when(i){
                    0 -> {
                        // Caller
                        galleryContract.launch("image/*")

                    }
                    1 ->{
                            cameraContract.launch(imageUri)

                    }
                }


            }).show()
    }

    private fun createImageUri(): Uri? {
        val image = File(applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(
            applicationContext,
            "com.example.camerapicimage.fileProvider",
            image
        )
    }
}