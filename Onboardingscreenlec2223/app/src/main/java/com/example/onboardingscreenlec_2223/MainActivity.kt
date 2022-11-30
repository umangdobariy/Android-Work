package com.example.onboardingscreenlec_2223

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigationlec_21.Fragments.FavoriteFragment
import com.example.bottomnavigationlec_21.Fragments.HomeFragment
import com.example.bottomnavigationlec_21.Fragments.ProfileFragment
import com.example.bottomnavigationlec_21.Fragments.SearchFragment
import com.example.onboardingscreenlec_2223.adpter.CustomRecyclerAdapter
import com.example.onboardingscreenlec_2223.adpter.CustomVerticalAdapter
import com.example.onboardingscreenlec_2223.databinding.ActivityMainBinding
import com.example.onboardingscreenlec_2223.databinding.MovieHorizontalListBinding
import com.example.onboardingscreenlec_2223.modal.food
import com.example.onboardingscreenlec_2223.modal.movie

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var movieList = mutableListOf<movie>()
    private var foodList = mutableListOf<food>()
    private lateinit var adapter: CustomRecyclerAdapter
    private lateinit var madapter:CustomVerticalAdapter
    private lateinit var horizontalListBinding: MovieHorizontalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareData()

        adapter = CustomRecyclerAdapter(this,movieList)

        binding.rvList.adapter = adapter


        var  layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvList.layoutManager = layoutManager


        // vertical

        madapter = CustomVerticalAdapter(this,foodList)

        binding.recyclerView.adapter = madapter

        var mlayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = mlayoutManager


        binding.toolBar.title = "Home"
        setSupportActionBar(binding.toolBar)

        addFragment("Home", HomeFragment())



        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    addFragment("Home", HomeFragment())
                    true
                }
                R.id.action_favorite -> {
                    addFragment("Favourite", FavoriteFragment())
                    true
                }
                R.id.action_search -> {
                    addFragment("Search", SearchFragment())
                    true
                }
                R.id.action_profile -> {
                    addFragment("Profile", ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }


    private fun addFragment(title: String, fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment, title)
            addToBackStack(null)
            commit()
        }
        binding.toolBar.title = title

//        var manager = supportFragmentManager
//        var transaction = manager.beginTransaction()
//        transaction.replace(R.id.fragment_container, fragment, title)
//        transaction.addToBackStack(null)
//        transaction.commit()
    }



    private fun prepareData() {

        movieList.add(movie(1,"Tarzan","Car",R.drawable.image7))
        movieList.add(movie(2,"Dhoom","Bike",R.drawable.image10))
        movieList.add(movie(3,"Bahubali","yudhaa",R.drawable.image6))
        movieList.add(movie(4,"Ramlila","Love",R.drawable.image8))

        foodList.add(food(1,"Burger",254.25f,2.5f,R.drawable.image1,2002))
        foodList.add(food(1,"Pizza",454.23f,2.25f,R.drawable.image2,2003))
        foodList.add(food(3,"pani puri",125.23f,3.5f,R.drawable.image3,2004))
        foodList.add(food(4,"sandwich",789.23f,4.5f,R.drawable.image4,2005))
        foodList.add(food(5,"hotdog",456.23f,1.5f,R.drawable.image5,2006))
    }
}