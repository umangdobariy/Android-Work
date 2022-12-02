package com.example.nestedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nestedrecyclerview.adapter.CategoryAdapter
import com.example.nestedrecyclerview.databinding.ActivityMainBinding
import com.example.nestedrecyclerview.modal.Category
import com.example.nestedrecyclerview.modal.SubCategory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var categoryList:MutableList<Category>
    lateinit var parentAdapter:CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        preparaData()

        parentAdapter = CategoryAdapter(this,categoryList)
        binding.parentView.layoutManager = LinearLayoutManager(this)
        binding.parentView.adapter = parentAdapter

        parentAdapter.setOnItemClickListener(object : CategoryAdapter.ItemClickListener{
            override fun ViewAllClicked(id: Int, position: Int, title: String) {
                Toast.makeText(applicationContext, "$title:$position :", Toast.LENGTH_SHORT).show()
            }

            override fun OnChildItemClicked(view: View, subCategory: SubCategory) {
                Toast.makeText(applicationContext, "${subCategory.name} : ${subCategory.id}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun preparaData() {

        var movieList = mutableListOf<SubCategory>()

        movieList.add(SubCategory(1,"Baahubali",R.drawable.image6,2.5f))
        movieList.add(SubCategory(2,"Tarzan",R.drawable.image7,3.5f))
        movieList.add(SubCategory(3,"Ramlila",R.drawable.image8,2.5f))
        movieList.add(SubCategory(4,"Naadi",R.drawable.image9,4.5f))
        movieList.add(SubCategory(5,"Tarzanar",R.drawable.image10,3.5f))

        categoryList = mutableListOf()
        categoryList.add(Category(1,"Top 10",movieList))
        categoryList.add(Category(2,"Recently Added",movieList))
        categoryList.add(Category(3,"Action Movies",movieList))
        categoryList.add(Category(4,"Horror movies",movieList))


    }
}