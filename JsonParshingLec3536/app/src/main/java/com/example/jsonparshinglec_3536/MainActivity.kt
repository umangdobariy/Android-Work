package com.example.jsonparshinglec_3536

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.jsonparsingandroid.adapter.RecyclerUserAdapter
import com.example.jsonparshinglec_3536.databinding.ActivityMainBinding
import com.example.jsonparshinglec_3536.modal.DataRespones
import com.example.jsonparshinglec_3536.network.ApiClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.security.Provider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mAdapter: RecyclerUserAdapter
    var isScrolling= false
    var currentItem = 0
    var totalItem: Int = 0
    var ScrolloutItem: Int = 0

//    private lateinit var binding: ActivityMainBinding
//    private var userList = ArrayList<User>()
//    private lateinit var mAdapter: RecylcerviewAdapter

    var page =1
    //lateinit var scrollListner: LoadMoreScroll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // userList= ArrayList()

        mAdapter = RecyclerUserAdapter(this, mutableListOf())
        var manager = LinearLayoutManager(this)
        binding.recyclerviewItem.layoutManager = manager
        binding.recyclerviewItem.adapter = mAdapter


        getUserList(page)

//        mAdapter = RecylcerviewAdapter(this, userList)
//        mAdapter.notifyDataSetChanged()
//        var manager = LinearLayoutManager(this)
//        binding.recyclerviewItem.layoutManager = manager
//        binding.recyclerviewItem.adapter = mAdapter

        //mLayoutManager = LinearLayoutManager(this)
        //items_rv.layoutManager = mLayoutManager
        //items_rv.setHasFixedSize(true)
        //manager = LinearLayoutManager(this)

//        scrollListner = LoadMoreScroll(manager as LinearLayoutManager)
//        scrollListner.setOnLoadMoreListener(object : OnLoadMoreListner {
//            override fun onLoadMore() {
//                LoadMoreData()
//            }
//        })
//        binding.recyclerviewItem.addOnScrollListener(scrollListner)

      //  binding.progressCircular.visibility = View.GONE

        binding.recyclerviewItem.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                    binding.progressCircular.visibility = View.VISIBLE
                    page++
                    getUserList(page)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                ScrolloutItem = manager.findFirstVisibleItemPosition()

                if (isScrolling && (currentItem + ScrolloutItem == totalItem)) {
                    isScrolling = false
                    binding.progressCircular.visibility = View.GONE
                    //++page
                    //getUserList(page)
                }
            }

        })

       // getUserList()
    }

    private fun getUserList(page:Int) {

        binding.progressCircular.visibility = View.VISIBLE

        var res: Call<DataRespones> = ApiClient.init().getUserList(page)

        res.enqueue(object : Callback<DataRespones> {
            override fun onResponse(call: Call<DataRespones>, response: Response<DataRespones>) {
                // request success
                 binding.progressCircular.visibility = View.GONE
                if (response.isSuccessful) {

                    var res = response.body()

                    res?.let {
                        mAdapter.setItems(it.userList)
                    }

                }
            }

            override fun onFailure(call: Call<DataRespones>, t: Throwable) {
                // request failed
                    binding.progressCircular.visibility = View.GONE
            }

        })


//        binding.progressCircular.visibility = View.VISIBLE
//
//
//        Handler().postDelayed({
//
//            var res: Call<DataRespones> = ApiClient.init().getUserList(page)
//
//            res.enqueue(object : Callback<DataRespones> {
//                override fun onResponse(
//                    call: Call<DataRespones>,
//                    response: Response<DataRespones>
//                ) {
//                    // request success
//                    binding.progressCircular.visibility = View.GONE
//                    if (response.isSuccessful) {
//
//                        var res = response.body()
//
//                        res?.let {
//                            for (i in 0..userList.size) {
//                                mAdapter.setItems(it.userList)
//                                mAdapter.notifyDataSetChanged()
//                            }
//
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<DataRespones>, t: Throwable) {
//                    // request failed
//                    binding.progressCircular.visibility = View.GONE
//                }
//
//            })
//
//        }, 1000)

    }


//    fun LoadMoreData() {
//
//        mAdapter.addLoadingView()
//        userList = ArrayList()
//
//        val start = mAdapter.itemCount
//        var end = start + 5
//
//        Handler().postDelayed({
//            for (i in start..end) {
//                getUserList()
//                page++
//              //  userList.add(User(0,userList))
//            }
//
//            mAdapter.removeLoadingView()
//            mAdapter.addData(userList)
//            scrollListner.setLoaded()
//
//            binding.recyclerviewItem.post {
//                mAdapter.notifyDataSetChanged()
//            }
//        },1000)
//
//
//    }


}