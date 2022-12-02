package com.example.food.fregment

import android.Manifest
import android.R.attr
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.food.Activity.MainActivity
import com.example.food.Prefrence.Prefrence
import com.example.food.databinding.FragmentProfileBinding
import com.example.food.databinding.LogoutDilogboxBinding
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.IOException


class ProfileFragment : Fragment() {
    // private val CAMERA_REQUEST = 10
    // private val MY_CAMERA_PERMISSION_CODE = 100
    lateinit var binding: FragmentProfileBinding
    lateinit var imageUri: Uri
    /*  private val PERMISSIONS: Array<String> = arrayOf(
          android.Manifest.permission.CAMERA,
          android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
          android.Manifest.permission.READ_EXTERNAL_STORAGE,
      )*/

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            it.entries.forEach {
                Log.e("Debug", "${it.key} = ${it.value}")
            }
        }


    //gellary context
    var gellarypic = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            binding.pImg.setImageURI(it)

            uritobitmap(it)?.let {
                saveImagetoExternalStorage(it)
            }


        }
    }


    var camerapic = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        /*imageUri.let { it->

        }*/
        binding.pImg.setImageURI(null)
        if (it) {

            binding.pImg.setImageURI(imageUri)
            // Glide.with(this).load(imageUri).into(binding.pImg);
            uritobitmap(imageUri)?.let {
                saveImagetoExternalStorage(it)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        imageUri = createImage()!!


        if (ContextCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
            ||
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )

        }



        binding.pImg.setOnClickListener {

            ShowDilogOpetion()

        }


        binding.logout.setOnClickListener {
            Toast.makeText(requireActivity(), "clicke", Toast.LENGTH_SHORT).show()
            showDailogBox()

        }
        return (binding.root)

    }

    private fun createImage(): Uri {
        var image = File(requireActivity().filesDir, "camera_picture.png")
        return FileProvider.getUriForFile(
            requireActivity(),
            "com.example.food.fileProvider",
            image
        )
    }

    //TODO tack URI OF THE IMAGE AND RETURN BITMAP
    private fun uritobitmap(selectFileDescriptor: Uri): Bitmap? {
        //convert uri to BItmap
        try {
            val parcelFileDescriptor: ParcelFileDescriptor = requireActivity().contentResolver.openFileDescriptor(selectFileDescriptor, "r")!!
            var fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
            var image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun saveImagetoExternalStorage(bitmap: Bitmap) {
        var ImageCollation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)

            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        else
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            var resolver = requireActivity().contentResolver
            var contentValues = ContentValues()
            contentValues.put(
                MediaStore.MediaColumns.DISPLAY_NAME, "${System.currentTimeMillis()}.jpg"
            )
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "Image/jpg")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "FOOD"
            )
            var imageuri1 = resolver.insert(ImageCollation, contentValues)

            if (imageuri1 != null) {
                requireActivity().contentResolver.openOutputStream(imageuri1)?.let {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                    Toast.makeText(requireActivity(), "image saved", Toast.LENGTH_SHORT).show()

                }
            }


        } else {
            var root = Environment.getExternalStorageDirectory()
            var path = File(root, "my Directory")
            if (!path.exists()) {
                path.mkdir()
            }
            val file = File(path, "${System.currentTimeMillis()}.jpg")
            if (!file.exists()) {
                try {
                    val fos = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                    fos.flush()
                    fos.close()
                    Toast.makeText(requireActivity(), "Image Saved", Toast.LENGTH_SHORT).show()

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


    }

    private fun ShowDilogOpetion() {
        var optionarray = arrayOf("camera", "gellary")
        AlertDialog.Builder(requireActivity())
            .setTitle("Pick Image")
            .setItems(optionarray, DialogInterface.OnClickListener { dialogInterface, i ->
                if (i == 0) {
                    //camera
                    //  checkPermissionCamera()
                    camerapic.launch(imageUri)
                } else if (i == 1) {
                    //gellary
                    gellarypic.launch("image/*")

                }

            }).show()


    }
/*

    private fun checkPermissionCamera() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED) {
           // ActivityCompat.requestPermissions(requireActivity(),  {Manifest.permission.CAMERA}, 5);
            ActivityCompat.requestPermissions(requireActivity(),PERMISSIONS,MY_CAMERA_PERMISSION_CODE)
        } else {
           // camerapic.launch(imageUri)
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //    camerapic.launch(imageUri)
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
            else{
                Toast.makeText(requireActivity(), "camera persssion denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            val photo = data?.extras?.get("data") as Bitmap
            binding.pImg.setImageBitmap(photo)
        }
    }
*/

    private fun showDailogBox() {
        var bind = LogoutDilogboxBinding.inflate(layoutInflater)
        var builder = AlertDialog.Builder(requireActivity())
            .setView(bind.root)
        var dilog = builder.create()
        dilog.show()
        bind.btnOk.setOnClickListener {
            var manger = Prefrence(requireActivity())
            manger.SetLoginStatus(false)
            manger.Logout()
            //Toast.makeText(requireActivity(), "ok", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            Toast.makeText(requireActivity(), "clear Data ", Toast.LENGTH_SHORT).show()

        }
        // dilog.dismiss()


    }


}