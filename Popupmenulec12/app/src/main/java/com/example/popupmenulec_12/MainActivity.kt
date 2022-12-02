package com.example.popupmenulec_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.example.popupmenulec_12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.udPopupmenu.setOnClickListener { it ->

            var popupMenu = PopupMenu(this, it)
            menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                return@setOnMenuItemClickListener when (it.itemId) {
                    R.id.action_Search -> {
                        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_Logout -> {
                        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_Profile -> {
                        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_Help -> {
                        Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }
}
