package com.example.allow_denypermisson

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    val REQ_STORAGE = 111


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.ud_external).setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                askStoragePermission()
            }else{
                Toast.makeText(applicationContext, "Permission allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun askStoragePermission() {
         if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)
        {

            Toast.makeText(applicationContext, "Permission already allowed", Toast.LENGTH_SHORT).show()
        }else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE))

                showPermissionDialog()
        else{

                // false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),REQ_STORAGE)
            }
        }
    }

    private fun showPermissionDialog() {


        AlertDialog.Builder(this).apply {
            setTitle("Permission Alert")
                .setMessage("Please allow this permission to read data from your external storage.")
                .setPositiveButton("Allow",  { dialogInterface, i ->

                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)

                }).setNegativeButton("Cancel",  { dialogInterface, i ->

                }).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQ_STORAGE){

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(applicationContext, "User allowed Permission ", Toast.LENGTH_SHORT).show()
            }else{

                Toast.makeText(applicationContext, "User denied Permission", Toast.LENGTH_SHORT).show()
            }
        }
    }
}