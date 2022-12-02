package com.example.firsttosecondfragmentdatabaselec_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firsttosecondfragmentdatabaselec_9.Fragments.FristFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // add login fragment to container

        var fragemt = FristFragment()
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.add(R.id.fragment_container,fragemt)
        transaction.commit()
    }
}