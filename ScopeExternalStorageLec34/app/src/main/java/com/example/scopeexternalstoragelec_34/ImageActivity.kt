package com.example.scopeexternalstoragelec_34

import android.Manifest
import android.content.ContentValues
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.scopeexternalstoragelec_34.databinding.ActivityImageBinding
import com.example.scopeexternalstoragelec_34.databinding.ActivityMainBinding
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.IOException

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    val galleryContract = registerForActivityResult(ActivityResultContracts.GetContent()){
        it.let {
            // binding.ivProfile.setImageURI(it)
            if (it != null) {
                uriToBitmap(it)?.let {
                    binding.ivProfile.setImageBitmap(it)

                    saveImageToExternalStorage(it)

                }
            }

        }
    }


    lateinit var imageUri: Uri

    val cameraContract = registerForActivityResult(ActivityResultContracts.TakePicture()){
        if (it){
            // binding.ivProfile.setImageURI(imageUri)

            uriToBitmap(imageUri).let { it ->
                binding.ivProfile.setImageBitmap(it)

                if (it != null) {
                    saveImageToExternalStorage(it)
                }
            }
        }
    }

    val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            it.entries.forEach {
                Log.e("DEBUG", "${it.key} = ${it.value}")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {

        } else {

            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }

        imageUri = createImageUri()!!
        binding.ivProfile.setOnClickListener {

            showOptionDialog()
        }

    }

    private fun saveImageToExternalStorage(bitmap: Bitmap) {


        val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        else
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

            var contentValues = ContentValues()

            contentValues.put(
                MediaStore.MediaColumns.DISPLAY_NAME,
                "${System.currentTimeMillis()}.png"
            )
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")

            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "Tops"       // pictures/tops
            )

            var resolver = contentResolver
            var imageUri = resolver.insert(imageCollection, contentValues)

            imageUri?.let {
                resolver.openOutputStream(imageUri)?.let {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                    Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show()
                }
            }

        }else{
            val root = Environment.getExternalStorageDirectory()
            val path = File(root, "TopsData")
            Log.d("TAG", "saveImageToExternalStorage: "+path)

            if (!path.exists()) {
                path.mkdir()
            }

            var fileName = "${System.currentTimeMillis()}.png"
            val file = File(path, fileName)


            if (!file.exists()) {
                try {
                    val fos = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                    fos.flush()
                    fos.close()

                    Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
    //TODO takes URI of the image and returns bitmap
    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
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

//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//
//                            if (checkCameraPermission()){
//                                pickImageFromCamera()
//                            }else{
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                    requestPermissions(arrayOf(Manifest.permission.CAMERA),100)
//                                }
//
//                            }
//                        }else{
//                            pickImageFromCamera()
//                        }
                    }
                }


            }).show()
    }

    private fun createImageUri(): Uri? {
        val image = File(applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(
            applicationContext,
            "com.example.scopeexternalstoragelec_34.fileProvider",
            image
        )
    }
}