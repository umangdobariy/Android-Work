package com.example.mvplec_46.activites.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonparshinglec_3536.modal.User
import com.example.mvplec_46.adapter.CustomRecyclerAdapter
import com.example.mvplec_46.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {


    lateinit var binding: ActivityMainBinding
    private var userList = mutableListOf<User>()
    private lateinit var mAdapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecycler()
        loadUserList()

    }

    private fun loadUserList() {
        var presenter = MainPresenter(this)
        presenter.loadUserList()
    }

    private fun initRecycler() {

        var manager = LinearLayoutManager(this)
        binding.recyclerItem.layoutManager = manager
        mAdapter = CustomRecyclerAdapter(this, userList)
        binding.recyclerItem.adapter = mAdapter
    }

    override fun success(userList: List<User>) {
        mAdapter.setItems(userList as MutableList<User>)
    }

    override fun failure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }


}