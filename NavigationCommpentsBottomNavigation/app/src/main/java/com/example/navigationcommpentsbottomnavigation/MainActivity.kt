package com.example.navigationcommpentsbottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContanair) as NavHostFragment

        navController = navHostFragment.navController

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        setupWithNavController(bottomNavigation,navController)
    }
}