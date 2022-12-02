package com.example.customrecyclerviewlec_17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customrecyclerviewlec_17.Modal.Food
import com.example.customrecyclerviewlec_17.Modal.movie
import com.example.customrecyclerviewlec_17.adpter.CustomHorizontalRecycleAdapter
import com.example.customrecyclerviewlec_17.adpter.CustomRecycleAdapter
import com.example.customrecyclerviewlec_17.databinding.ActivityMainBinding
import com.example.customrecyclerviewlec_17.listener.OnListitemClickListener

class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private var itemList = mutableListOf<Food>()
     private var movieList = mutableListOf<movie>()
     private lateinit var madpter:CustomRecycleAdapter
     private lateinit var horizontalAdapter: CustomHorizontalRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        prepareData()


        madpter = CustomRecycleAdapter(this,itemList)

        var manager = LinearLayoutManager(this)

        binding.recyclerView.layoutManager = manager

        binding.recyclerView.adapter = madpter



        madpter.setOnlistItemClickListener(object : OnListitemClickListener{
            override fun onCardClicked(pos: Int, movie: movie) {
            }

            override fun onImageClicked(view: View, pos: Int) {
                var intent = Intent(applicationContext,VarticalActivity::class.java)
                intent.putExtra("title",itemList.get(pos).Name)
                intent.putExtra("type",itemList.get(pos).type)
                intent.putExtra("rating",itemList.get(pos).rating)
                intent.putExtra("img",itemList.get(pos).image)
                intent.putExtra("Price",itemList.get(pos).Price)
                intent.putExtra("year",itemList.get(pos).year)
                startActivity(intent)

            }

            override fun onFoodClicked(pos: Int, food: Food) {

                Toast.makeText(applicationContext, "${food.type}", Toast.LENGTH_SHORT).show()
            }


        })


        //horizontal
        horizontalAdapter = CustomHorizontalRecycleAdapter(this,movieList)
        manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerViewHori.layoutManager = manager

        binding.recyclerViewHori.adapter = horizontalAdapter

        horizontalAdapter.setOnlistItemClickListener(object : OnListitemClickListener  {
            override fun onCardClicked(pos: Int, movie1: movie) {
                Toast.makeText(applicationContext, "${movie1.title}", Toast.LENGTH_SHORT).show()
               // var intent = Intent(applicationContext,HomeActivity::class.java)
                //intent.putExtra("title",movie1.title)
//                intent.putExtra("type",movie1.type)
//                intent.putExtra("rating",movie1.rating)
//                intent.putExtra("img",movie1.image)
//                startActivity(intent)
            }

            override fun onImageClicked(view: View, pos: Int) {
                Toast.makeText(applicationContext, "Image - $pos", Toast.LENGTH_SHORT).show()


                //Toast.makeText(applicationContext, "${movie.title}", Toast.LENGTH_SHORT).show()
                var intent = Intent(applicationContext,HomeActivity::class.java)
                intent.putExtra("title",movieList.get(pos).title)
                intent.putExtra("type",movieList.get(pos).type)
                intent.putExtra("rating",movieList.get(pos).rating)
                intent.putExtra("img",movieList.get(pos).image)
               startActivity(intent)


            }

            override fun onFoodClicked(pos: Int, food: Food) {

            }


        })




    }

    private fun prepareData() {

        itemList.add(Food(1,"Burger","Beef Burger",250.25f,2.5f,R.drawable.image1,2002))
        itemList.add(Food(2,"Pizza","Garlic Pizza",122.23f,3.5f,R.drawable.image2,2003))
        itemList.add(Food(3,"Pani puri","Limi puri",70.25F,1.5f,R.drawable.image3,2004))
        itemList.add(Food(4,"Sandwich","Gril Sandwich",133.25f,4.2f,R.drawable.image4,2005))
        itemList.add(Food(5,"Hotdog","Corn dog",252.23f,4.5f,R.drawable.image5,2006))

        movieList.add(movie("Baahubali","action",2.5f,R.drawable.image6))
        movieList.add(movie("Tarzan","Car",3.5f,R.drawable.image7))
        movieList.add(movie("Ramlila","loaded",4.5f,R.drawable.image8))
        movieList.add(movie("Naadi Dosh","Love",2.5f,R.drawable.image9))
        movieList.add(movie("Tarzan","car",2.5f,R.drawable.image10))


    }
}