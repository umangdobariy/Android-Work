package com.example.food.fregment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.Activity.Hori_DetailsActivity
import com.example.food.Activity.Seeall_Activity
import com.example.food.Interface.OnHomeClickeListner
import com.example.food.Interface.OnHoriClickeListner
import com.example.food.Interface.OnParClickeListner
import com.example.food.R
import com.example.food.adaptar.home_adaptor
import com.example.food.adaptar.hori_adaptor
import com.example.food.adaptar.par3_titAdoptor
import com.example.food.databinding.FragmentHomeBinding

import com.example.food.modal.*
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale.filter


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    private var homelist = mutableListOf<home>()
    lateinit var hadaptor : home_adaptor


    lateinit var dtabase:FirebaseDatabase

    //hori list adaptor
    private var horilist = mutableListOf<hor_list>()
    lateinit var hori_adoptor: hori_adaptor
    // var row_index:Int = -1

    //par3 list adaptor

    private var p3list = mutableListOf<par3_tital>()
    var subhomlist = mutableListOf<sub_home>()
    lateinit var padaptor: par3_titAdoptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homelist = mutableListOf()


        preparedata()


        // imagelist firebase

        hadaptor = home_adaptor(requireActivity(), homelist)
        var manager = LinearLayoutManager(requireActivity())
        //manager.orientation=LinearLayoutManager.HORIZONTAL
        binding.parent2.layoutManager = manager
        binding.parent2.adapter = hadaptor
       // hadaptor.notifyDataSetChanged()

        hadaptor.homelistner(object : OnHomeClickeListner {
            override fun homelistlistner(view: View, shome: sub_home, pos: Int){

                var intent = Intent(requireActivity(), Hori_DetailsActivity::class.java)
                intent.putExtra("subhome", subhomlist.get(pos))
                startActivity(intent)


                Toast.makeText(requireActivity(), "${shome.Tit},$pos", Toast.LENGTH_SHORT).show()

            }

        })


        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                hadaptor.func(newText)
                return false
            }

        })


        hori_adoptor = hori_adaptor(requireActivity(), horilist)
        binding.parent1.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.parent1.adapter = hori_adoptor


        // Inflate the layout for this fragment

        //hori nu clicke evevent
        hori_adoptor.clickelistner(object : OnHoriClickeListner {
            override fun horilistner(hori: hor_list, pos: Int) {
                // row_index = pos
                // hori_adoptor.notifyDataSetChanged()


                // var list = hor_list(hori.id, hori.Img)
                //var bundal = Bundle()

                // bundal.putParcelable("obj",list)

                //var fregment = CartFragment()
                // fregment.arguments = bundal

//                requireActivity()
//                    .supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fregment)
//                    .commit()
                Toast.makeText(requireActivity(), "$pos", Toast.LENGTH_SHORT).show()

            }


        })



        //parent 3 recycle view
        padaptor = par3_titAdoptor(requireActivity(), p3list)
        binding.parent3.layoutManager = LinearLayoutManager(requireActivity())
        binding.parent3.adapter = padaptor

        //parent 3 nu clicke event
        padaptor.clickeEvent(object : OnParClickeListner {
            override fun Seeall(id: Int, position: Int, tit: String) {

                startActivity(Intent(requireActivity(), Seeall_Activity::class.java))
                // Toast.makeText(requireActivity(), "$tit", Toast.LENGTH_SHORT).show()
            }

            override fun childClicke(view: View, seeall: par3_seeall, position: Int) {
                Toast.makeText(requireActivity(), "${seeall.recto}", Toast.LENGTH_SHORT).show()
            }

        })



        return (binding.root)
    }


    private fun preparedata() {

        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))
        horilist.add(hor_list(1, R.drawable.clock))






        subhomlist.apply {
            this.add(sub_home(1, R.drawable.burger, "Burger", "$123.90"))
            this.add(sub_home(2, R.drawable.burger, "Pancake", "$123.90"))
            this.add(sub_home(3, R.drawable.burger, "Soft-Drink", "$123.90"))
            this.add(sub_home(4, R.drawable.burger, "Cold-Drink", "$123.90"))
            this.add(sub_home(5, R.drawable.apple, "Pancake", "$103.90"))
            this.add(sub_home(6, R.drawable.burger, "Soft-Drink", "$123.90"))
            this.add(sub_home(7, R.drawable.burger, "Cold-Drink", "$123.90"))
            this.add(sub_home(8, R.drawable.burger, "Burger", "$123.90"))
            this.add(sub_home(9, R.drawable.burger, "Soft-Drink", "$193.90"))
            this.add(sub_home(10, R.drawable.burger, "Burger", "$123.90"))
            this.add(sub_home(11, R.drawable.burger, "Soft-Drink", "$123.90"))
            this.add(sub_home(12, R.drawable.burger, "Cold-Drink", "$183.90"))

           // hadaptor.notifyDataSetChanged()

        }


        homelist = mutableListOf()
        homelist.add(home(1, "Food Menu", subhomlist))


        var p3see = mutableListOf<par3_seeall>()
        p3see.add(par3_seeall(1, R.drawable.par3, "Amiras", "13,simada naka", "12:30pm", 4.2f))
        p3see.add(par3_seeall(2, R.drawable.par3, "Surbhi", "13,simada naka", "12:30pm", 4.2f))
        p3see.add(par3_seeall(3, R.drawable.par3, "A-One", "13,simada naka", "12:30pm", 4.2f))
        p3see.add(par3_seeall(4, R.drawable.par3, "Dilgital", "13,simada naka", "12:30pm", 4.2f))
        p3see.add(par3_seeall(5, R.drawable.par3, "subway", "13,simada naka", "12:30pm", 4.2f))
        p3see.add(par3_seeall(6, R.drawable.par3, "Mc-Donld", "13,simada naka", "12:30pm", 4.2f))
        p3see.add(par3_seeall(7, R.drawable.par3, "Pasta Purim", "13,simada naka", "12:30pm", 4.2f))


        //parent 3 list
        p3list = mutableListOf()
        p3list.add(par3_tital(1, "Near Me", p3see))

        //hadaptor.notifyDataSetChanged()

    }


}