package com.example.food.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.food.R
import com.example.food.databinding.ActivityHomeBinding
import com.example.food.fregment.*

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var toogle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val view = currentFocus
//        if (view != null) {
//            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(view.windowToken, 0)
//        }

        var toolbar = binding.toolbar
        var drawer = binding.drawerlayout
        var navigration = binding.navigationView

        //toogle = ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close)
        //drawer.addDrawerListener(toogle)
        //toogle.syncState()

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /* navigration.setNavigationItemSelectedListener {

           true
         }*/
        // side swipe close this code
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toolbar.setNavigationOnClickListener {
            drawer.open()
        }
        navigration.setNavigationItemSelectedListener { menuitem ->
            menuitem.isChecked = true
            drawer.close()
            true
        }
        //Set By Defalat page
        addFregment(HomeFragment(), "Home")

        binding.bottomNavigation.setOnItemSelectedListener {


            return@setOnItemSelectedListener when (it.itemId) {
                R.id.home -> {
                    addFregment(HomeFragment(), "Home")
                    true
                }
                R.id.category -> {
                    addFregment(CategoryFragment(), "Category")
                    true
                }
                R.id.cart -> {
                    addFregment(CartFragment(), "Cart")
                    true
                }
                R.id.profile -> {
                    addFregment(ProfileFragment(), "Profile")
                    true
                }
                else -> {
                    false
                }


            }

        }


        //navigration drwer clicke event
        binding.navigationView.setNavigationItemSelectedListener {

            true
        }
        navigration.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.profile -> {
                    addFregment(ProfileFragment(),"Profile")
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    menuItem.isChecked = false
                }
                R.id.cart -> {
                    addFregment(CartFragment(),"Cart")
                    Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show()
                    menuItem.isChecked = false
                }
                R.id.order -> {
                    addFregment(OrderFragment(),"Order")
                    Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show()
                    menuItem.isChecked = false
                }
                R.id.about -> {
                    addFregment(AboutFragment(),"About")
                    Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
                    menuItem.isChecked = false
                }
                R.id.change -> {
                    addFregment(ChangeFragment(),"Change")
                    Toast.makeText(this, "Change", Toast.LENGTH_SHORT).show()
                    menuItem.isChecked = false
                }
                R.id.logout -> {
                    addFregment(LogoutFragment(),"Logout")
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                    menuItem.isChecked = false
                }
            }
            drawer.close()
            true
        }

    }

    //horizontal list in data seting
    private fun setData() {

    }

    private fun addFregment(fragment: Fragment, tag: String) {
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, tag)
        transaction.commit()

        //toolbar ma set
        //binding.toolBar.title = tag

    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return super.onSupportNavigateUp()

    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}