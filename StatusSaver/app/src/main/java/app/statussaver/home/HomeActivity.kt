package app.statussaver.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.statussaver.R
import app.statussaver.databinding.ActivityHomeBinding
import app.statussaver.savestatus.adapter.NavigationDrawerAdapter
import app.statussaver.savestatus.fragment.MessageRecovery
import app.statussaver.savestatus.model.NavigationModel
import app.statussaver.savestatus.tabFragment.SavedFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var adapter: NavigationDrawerAdapter
    var listdata = mutableListOf<NavigationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_home)
        setSupportActionBar(binding.toolbar)
        initview()

    }

    fun initview() {

//        binding.rvSlide.addOnItemTouchListener(RecyclerViewTouchListner(
//
//        ))

        listdata.add(NavigationModel(R.drawable.image_logo, "Home"))
        listdata.add(NavigationModel(R.drawable.image_logo, "status"))

        binding.rvSlide.layoutManager=LinearLayoutManager(this)

        binding.rvSlide.addOnItemTouchListener(RecyclerViewTouchListner(this, object :
            RecyclerViewTouchListner.ClickListner {
            override fun onClick(view: View, postion: Int) {
                when (postion) {
                    0 -> {
                        addFragmentView("saveStatus", SavedFragment())
                    }
                    1 -> {
                        addFragmentView("MessageSave", MessageRecovery())
                    }

                }
                Handler().postDelayed(
                    {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }, 1000
                )
            }
        }))

        var toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                try {
                    var inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                try {
                    var inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


//        if (position != && position != ) {
//            updateAdapter(position)
//        }
//        Handler().postDelayed({
//            drawerLayout.closeDrawer(GravityCompat.START)
//        }, 200)


        adapter = NavigationDrawerAdapter(this, listdata)
        binding.rvSlide.layoutManager = LinearLayoutManager(this)
        binding.rvSlide.adapter = adapter
        binding.rvSlide.setHasFixedSize(true)

        //updateAdapter()

    }

    fun addFragmentView(title: String, fragemt: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, fragemt, title)
            .addToBackStack(null)
            .commit()

    }

    fun updateAdapter(pos: Int) {
       // adapter = NavigationDrawerAdapter(listdata, pos)
       // binding.rvSlide.adapter = adapter
       // adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }

}