package app.statussaver.savestatus

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
import app.statussaver.databinding.ActivityStatusBinding
import app.statussaver.home.RecyclerViewTouchListner
import app.statussaver.savestatus.adapter.NavigationDrawerAdapter
import app.statussaver.savestatus.fragment.MessageRecovery
import app.statussaver.savestatus.fragment.SaveStatusFragment
import app.statussaver.savestatus.model.NavigationModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class statusActivity : AppCompatActivity() {

    lateinit var binding: ActivityStatusBinding
    lateinit var adapter: NavigationDrawerAdapter
    var listdata = mutableListOf<NavigationModel>()
    lateinit var bottomview:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomview =findViewById(R.id.bottomview)
        setSupportActionBar(binding.toolbar)

        initview()
        drawerLayout()
    }

    fun initview() {

//        addFragmentView("saveStatus", SaveStatusFragment())
//
//        binding.bottomview.setOnItemSelectedListener {
//            return@setOnItemSelectedListener when (it.itemId) {
//                R.id.action_saveStatus -> {
//                    addFragmentView("saveStatus", SaveStatusFragment())
//                    true
//                }
//                R.id.action_message -> {
//                        addFragmentView("MessageSave", MessageRecovery())
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }


        addFragmentView("saveStatus", SaveStatusFragment())
        bottomview.setOnItemSelectedListener {
            return@setOnItemSelectedListener when(it.itemId){
                R.id.action_saveStatus->{
                    addFragmentView("saveStatus", SaveStatusFragment())
                    true
                }
                R.id.action_message->{
                    addFragmentView("MessageSave", MessageRecovery())
                    true
                }
              else->{
                  false
              }
            }

        }

    }


    fun drawerLayout() {

        listdata.add(NavigationModel(R.drawable.image_logo, "status"))
        listdata.add(NavigationModel(R.drawable.image_logo, "Message"))

        binding.rvSlide.layoutManager= LinearLayoutManager(this)

        //addFragmentView("saveStatus", SaveStatusFragment())
        binding.rvSlide.addOnItemTouchListener(RecyclerViewTouchListner(this, object :
            RecyclerViewTouchListner.ClickListner {
            override fun onClick(view: View, postion: Int) {
                when(postion)
                {
                    0-> {
                        addFragmentView("saveStatus", SaveStatusFragment())
                    }
                    1-> {
                        addFragmentView("MessageSave", MessageRecovery())
                    }

                }
                Handler().postDelayed(
                    {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }, 300
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

//    fun addNavigationFragmentView(title: String, fragemt: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.containerView, fragemt, title)
//            .addToBackStack(null)
//            .commit()
//
//    }

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
        super.onBackPressed()
//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//        }
//        else
//        {
//            if (supportFragmentManager.backStackEntryCount > 0) {
//                supportFragmentManager.popBackStack()
//            } else {
//                super.onBackPressed()
//            }
//        }
    }

}




