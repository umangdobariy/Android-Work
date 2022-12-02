package com.example.navigationdrawerlec_52.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationdrawerlec_52.R
import com.example.navigationdrawerlec_52.databinding.ActivityMainBinding
import com.example.navigationdrawerlec_52.sharepref.SharePref
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var toolbar :Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        findViewById<Button>(R.id.btn_logout).setOnClickListener {

            var prefManagr = SharePref(this)
            prefManagr.setLoginStatus(false)

            startActivity(Intent(this,LoginActivity::class.java))
            finishAffinity()
        }

        val bottomView : BottomNavigationView = findViewById(R.id.bottom_navigation)
        toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        binding.appBarLayout.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val navController:NavHostController = findNavController(R.id.nav_host_fragment_content_drawer) as NavHostController


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_search, R.id.nav_cart, R.id.nav_profile
            ), binding.drawerLayout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        bottomView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}