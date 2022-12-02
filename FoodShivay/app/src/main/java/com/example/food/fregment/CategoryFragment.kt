package com.example.food.fregment


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.food.Interface.OnClickListner
import com.example.food.R
import com.example.food.adaptar.grid_Adaptor
import com.example.food.databinding.CategoryFragmentBinding
import com.example.food.modal.gridclass
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class CategoryFragment : Fragment() {
    lateinit var binding: CategoryFragmentBinding
    lateinit var Gadaptor: grid_Adaptor
    var Gridlist = mutableListOf<gridclass>()

    lateinit var db:FirebaseFirestore
    //lateinit var menu: MenuItem
   // private var searchView: SearchView? = null
    //private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = CategoryFragmentBinding.inflate(inflater, container, false)

        //DataInsert()
        //Gadaptor.notifyDataSetChanged()





        Gridlist = mutableListOf()


        db=FirebaseFirestore.getInstance()
        db.collection("userData")
            .addSnapshotListener { value, error ->

                var listdata= arrayListOf<gridclass>()
                var list=value?.toObjects(gridclass::class.java)
                listdata.addAll(list!!)

                Gadaptor = grid_Adaptor(requireActivity(), Gridlist)
                var manger = GridLayoutManager(context, 3)
                binding.gridView.layoutManager = manger
                binding.gridView.adapter = Gadaptor
            }


       /* Gadaptor = grid_Adaptor(requireActivity(), Gridlist)
        var manger = GridLayoutManager(context, 3)
        binding.gridView.layoutManager = manger
        binding.gridView.adapter = Gadaptor*/


      /*  val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue(Gridlist).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(requireActivity(), "sucess", Toast.LENGTH_SHORT).show()
            }

        }*/

        //DataInsert()
        //grident no clicke evevent .....

        Gadaptor.OngridListner(object : OnClickListner {
            // Toast.makeText(requireActivity(), "toast", Toast.LENGTH_SHORT).show()
            override fun gridviewclicke(Item: gridclass, pos: Int){

                Toast.makeText(requireActivity(), "${Item.name},$pos", Toast.LENGTH_SHORT).show()

            }
        })

        /* binding.etSearch.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(p0: String?): Boolean {
                 binding.etSearch.clearFocus()
                 return false
             }
             override fun onQueryTextChange(msg: String): Boolean {
                 filter(msg)
              return false
             }

         })*/


        binding.etSearch.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                   filter(newText)
                   return false
            }

        })

        return (binding.root)
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchManager = requireActivity().getSystemService(SEARCH_SERVICE) as SearchManager
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    Log.i("onQueryTextChange", newText)
                    filter(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.i("onQueryTextSubmit", query)
                    return true
                }
            }
            searchView?.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionSearch->{
                return false
            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }*/


    /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         // val inflater = menuInflater
         //val inflater: MenuInflater = getMenuInflater()
         inflater.inflate(R.menu.search_menu, menu)

         val searchItem: MenuItem = menu.findItem(R.id.actionSearch)
         //val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
         val searchView: SearchView = searchItem.getActionView() as SearchView

         searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

             override fun onQueryTextSubmit(p0: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(msg: String): Boolean {
                 // inside on query text change method we are
                 // calling a method to filter our recycler view.
                 filter(msg)
                 return false
             }

         })
        // return true
     }*/

    /*   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
           super.onCreateOptionsMenu(menu, inflater)

           //var inflater.inflate(R.menu.search_menu,menu)
           // val inflater = menuInflater
           //val inflater: MenuInflater = getMenuInflater()
           inflater.inflate(R.menu.search_menu, menu)

           val searchItem: MenuItem = menu.findItem(R.id.actionSearch)
           //val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
           val searchView: SearchView = searchItem.getActionView() as SearchView

           //searchItem.setOnMenuItemClickListener {  }

           searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

               override fun onQueryTextSubmit(p0: String?): Boolean {
                   return false
               }

               override fun onQueryTextChange(msg: String): Boolean {
                   // inside on query text change method we are
                   // calling a method to filter our recycler view.
                   filter(msg)
                   return false
               }

           })


           // return true

          // return true
       }*/


    private fun DataInsert() {

        Toast.makeText(requireActivity(), "dataInsert", Toast.LENGTH_SHORT).show()

        Gridlist.add(gridclass(1, R.drawable.pancake, "Pancake"))
        Gridlist.add(gridclass(2, R.drawable.pancake, "Honey"))
        Gridlist.add(gridclass(3, R.drawable.pancake, "juices"))
        Gridlist.add(gridclass(4, R.drawable.pancake, "Honey"))
        Gridlist.add(gridclass(5, R.drawable.pancake, "juices"))
        Gridlist.add(gridclass(6, R.drawable.pancake, "Pancake"))
        Gridlist.add(gridclass(7, R.drawable.pancake, "Pancake"))
        Gridlist.add(gridclass(8, R.drawable.pancake, "juices"))
        Gridlist.add(gridclass(9, R.drawable.pancake, "Honey"))
        Gridlist.add(gridclass(10, R.drawable.pancake, "Pancake"))
        Gridlist.add(gridclass(11, R.drawable.pancake, "juices"))
        Gridlist.add(gridclass(12, R.drawable.pancake, "Honey"))
        Gridlist.add(gridclass(13, R.drawable.pancake, "Honey"))
        Gridlist.add(gridclass(14, R.drawable.pancake, "Pancake"))
        Gridlist.add(gridclass(15, R.drawable.pancake, "Honey"))
        Gridlist.add(gridclass(16, R.drawable.pancake, "juices"))
        Gridlist.add(gridclass(17, R.drawable.pancake, "Honey"))


        Toast.makeText(requireActivity(), "notifider", Toast.LENGTH_SHORT).show()
        this.Gadaptor.notifyDataSetChanged()
        Toast.makeText(requireActivity(), "notifider  thyu", Toast.LENGTH_SHORT).show()

    }

    private fun filter(text: String) {
        val filterlist= mutableListOf<gridclass>()

        for (grid in Gridlist){
            if (grid.name.toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(grid)
            }
        }
        if (filterlist.isEmpty()){
            Toast.makeText(requireActivity(), "No Data Found", Toast.LENGTH_SHORT).show()
        } else {
            Gadaptor.filteredlist(filterlist)
        }

    }


}

