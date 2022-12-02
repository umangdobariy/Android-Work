package com.example.galleryandcamerapicimagelec_3132

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.galleryandcamerapicimagelec_3132.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    //Receiver

    val galleryContract = registerForActivityResult(ActivityResultContracts.GetContent()){
        it.let {
            binding.ivProfile.setImageURI(it)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivProfile.setOnClickListener {

            showOptionDialog()
        }
    }

    private fun showOptionDialog() {

        val items = arrayOf("From Gallery","From Camera")

        AlertDialog.Builder(this)
            .setTitle("Select option")
            .setItems(items,DialogInterface.OnClickListener { dialogInterface, i ->

                when(i){
                    0 -> {
                        // Caller
                        galleryContract.launch("image/*")

                    }
                    1 ->{

                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                           if (checkCameraPermission()){
                               pickImageFromCamera()
                           }else{
                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                   requestPermissions(arrayOf(Manifest.permission.CAMERA),100)
                               }

                           }
                       }else{
                           pickImageFromCamera()
                       }
                    }
                }


            }).show()
    }

    private fun pickImageFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent,200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 200){
            data?.let {
                binding.ivProfile.setImageBitmap(data.extras!!.get("data") as Bitmap)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100){

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

               pickImageFromCamera()
            }
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )== PackageManager.PERMISSION_GRANTED
    }

}