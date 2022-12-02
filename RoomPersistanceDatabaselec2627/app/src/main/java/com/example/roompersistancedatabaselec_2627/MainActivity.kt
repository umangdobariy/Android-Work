package com.example.roompersistancedatabaselec_2627

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.roompersistancedatabaselec_2627.adpter.UserAdapter
import com.example.roompersistancedatabaselec_2627.database.Appdatabase
import com.example.roompersistancedatabaselec_2627.database.entity.User
import com.example.roompersistancedatabaselec_2627.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var db:Appdatabase
    lateinit var binding: ActivityMainBinding
    private var userList = mutableListOf<User>()
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            this,Appdatabase::class.java, "tops_student").allowMainThreadQueries().build()

//        db.userDao.insertRecord()

        binding.btnAdd.setOnClickListener {

            startActivity(Intent(this,UserActivity::class.java))
        }

        adapter = UserAdapter(this,userList)
        binding.recyclerItem.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerItem.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        if (db!=null){
            userList = db.userDao.getUserList() as MutableList<User>
            adapter.setItems(userList)
        }

    }

}