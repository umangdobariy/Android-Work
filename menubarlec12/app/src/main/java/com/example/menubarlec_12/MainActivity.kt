package com.example.menubarlec_12

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu,menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId)
        {
            R.id.action_help->{
                Toast.makeText(applicationContext,"item selected :help ",Toast.LENGTH_SHORT).show()
                true
            }R.id.action_profile->{
                Toast.makeText(applicationContext,"item selected :profile ",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_logout->{
                showSimpleDialog()
                true
            }
            R.id.action_setting->{
                Toast.makeText(applicationContext,"item selected :setting ",Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSimpleDialog() {

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
            .setMessage("are you sure you want to logout from this application?")
            .setPositiveButton("Logout", DialogInterface.OnClickListener { dialog, i ->
                Toast.makeText(this,"Positive button clicked",Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(this,"Negative button clicked",Toast.LENGTH_SHORT).show()
            })
            .show()
    }
}
