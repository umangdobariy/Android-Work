package com.example.recycleviewcustom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewcustom.Adapter.CustomHorizontalRecycler
import com.example.recycleviewcustom.Adapter.CustomVerticalRecycler
import com.example.recycleviewcustom.Modal.Food9
import com.example.recycleviewcustom.Modal.movie
import com.example.recycleviewcustom.databinding.ActivityMainBinding
import com.example.recycleviewcustom.databinding.CustomProfileLayoutBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var itemList = mutableListOf<Food9>()
    private lateinit var madapter:CustomVerticalRecycler
    private var movieList = mutableListOf<movie>()
    private lateinit var uadpter:CustomHorizontalRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareData()

        // pass data to adpater class using constructor

        madapter =CustomVerticalRecycler(this,itemList)

        // adpater vertical or horizontal

        var layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHori.layoutManager = layoutManager

        // setdata to recyclerview using set adpter
        binding.recyclerViewHori.adapter = madapter


        // horizonatal data

        uadpter = CustomHorizontalRecycler(this,movieList)

        // adpater vertical or horizontal

        uadpter.setOnItemClickListener(object:CustomHorizontalRecycler.onItemClickListener{
            override fun onItemclicked(view: View, movie: movie) {
                Toast.makeText(applicationContext, movie.name, Toast.LENGTH_SHORT).show()
            }

            override fun onThumbnailSelected(view: View, movie: movie, position: Int) {

                showCustomDialog(movie)
                Toast.makeText(applicationContext, "Image ${movie.name}", Toast.LENGTH_SHORT).show()
            }

        })

        var ulayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.RecyclerView.layoutManager = ulayoutManager

        // setdata to recyclerview using set adpter
        binding.RecyclerView.adapter = uadpter

    }

    private fun showCustomDialog(movie: movie) {

        var bind = CustomProfileLayoutBinding.inflate(layoutInflater)

        var builder = AlertDialog.Builder(this)
            .setView(bind.root)

        bind.udThumbnail.setImageResource(movie.image)

        var dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    private fun prepareData() {

        itemList.add(Food9(1, "Burger", 235.25f, 2.5f, 2002, R.drawable.image1))
        itemList.add(Food9(2, "Pizaa", 250.33f, 3.5f, 2006, R.drawable.image2))
        itemList.add(Food9(1, "Panipuri", 125.41f, 4.5f, 2007, R.drawable.image3))
        itemList.add(Food9(1, "Panipuri", 214.89f, 4.1f, 2008, R.drawable.image4))
        itemList.add(Food9(1, "Hotdog", 123.75f, 1.5f, 2009, R.drawable.image5))


        movieList.add(movie("Bahubali","YOdha",1.2f,R.drawable.image6))
        movieList.add(movie("Tarzan","Love",1.9f,R.drawable.image7))
        movieList.add(movie("Ram lila","Fiting",2.2f,R.drawable.image8))
        movieList.add(movie("Nadidosh","Romentice",3.2f,R.drawable.image9))
        movieList.add(movie("Dhoom","Comedy",4.2f,R.drawable.image10))

    }
}