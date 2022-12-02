package com.example.food.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.R
import com.example.food.adaptar.See_All_Adaptar
import com.example.food.databinding.ActivitySeeallBinding
import com.example.food.databinding.SeeAllActivityBinding
import com.example.food.modal.gridclass
import com.example.food.modal.see_all
import java.util.Locale.filter

class Seeall_Activity : AppCompatActivity(){

    lateinit var binding: ActivitySeeallBinding
    lateinit var seeadaptor :See_All_Adaptar
    var seealllist = mutableListOf<see_all>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeallBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //seealllist = mutableListOf()
        seeadaptor = See_All_Adaptar(this,seealllist)

        var manger = LinearLayoutManager(this)
        binding.recView.layoutManager = manger
        binding.recView.adapter = seeadaptor

        PrepardData()

        binding.etSearch.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }


        })



    }

    private fun PrepardData() {
        seealllist.add(see_all(1,R.drawable.burg,"Amiras","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Surbhi","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Dilgital","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Surbhi","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Amiras","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Dilgital","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Surbhi","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Amiras","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Dilgital","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Surbhi","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Amiras","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Dilgital","145,amidhara sos,near simada naka","12:45PM",4.5f))
        seealllist.add(see_all(1,R.drawable.burg,"Surbhi","145,amidhara sos,near simada naka","12:45PM",4.5f))

        seeadaptor.notifyDataSetChanged()


    }

        private fun filter(text: String){
            val filterlist= mutableListOf<see_all>()

            for (grid in seealllist)
            {
                if (grid.recto.toLowerCase().contains(text.toLowerCase()))
                {
                    filterlist.add(grid)
                }
            }
            if (filterlist.isEmpty())
            {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                seeadaptor.filteredlist(filterlist)
            }

        }


}