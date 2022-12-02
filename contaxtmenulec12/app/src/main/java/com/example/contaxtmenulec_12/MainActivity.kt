package com.example.contaxtmenulec_12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.contaxtmenulec_12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerForContextMenu(binding.rdContext)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contaxtmenu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_search ->{
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_logout ->{
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_profile ->{
                Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_help ->{
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }


    }

}
