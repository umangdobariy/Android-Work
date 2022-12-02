package com.example.bestiu_k24_11_2022interviewpass

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.bestiu_k24_11_2022interviewpass.adapter.RecyclerAdapter
import com.example.bestiu_k24_11_2022interviewpass.database.Appdatabase
import com.example.bestiu_k24_11_2022interviewpass.database.entity.User
import com.example.bestiu_k24_11_2022interviewpass.databinding.ActivityMainBinding
import com.example.bestiu_k24_11_2022interviewpass.databinding.CustomUserDailogBinding
import com.example.bestiu_k24_11_2022interviewpass.listner.OnItemClickListner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db:Appdatabase
    private lateinit var adapter:RecyclerAdapter
    var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(this,Appdatabase::class.java, "tops-user.db").allowMainThreadQueries().build()


        adapter = RecyclerAdapter(this,userList)
        var manager = LinearLayoutManager(applicationContext)
        binding.ukRecyclerviewItem.layoutManager = manager

        binding.ukRecyclerviewItem.adapter = adapter


        adapter.setOnClickListner(object : OnItemClickListner{
            override fun OnDelateItem(user: User) {
                showDeleteDailog(user)
            }

            override fun OnUpdateItem(user: User) {
                showUpdateDailog(user)
            }

        })

        binding.ukSave.setOnClickListener {

            var name = binding.ukName.text.toString().trim()
            var email = binding.ukEmail.text.toString().trim()


            insertRecord(name,email)

        }
    }

    private fun showUpdateDailog(user: User) {

        var bind = CustomUserDailogBinding.inflate(layoutInflater)

        var builder = AlertDialog.Builder(this)
            .setView(bind.root)

        bind.ukaName.setText(user.name)
        bind.ukaEmail.setText(user.email)

        var alert = builder.create()
        alert.show()

        bind.ukaUpdate.setOnClickListener {

            var name = bind.ukaName.text.toString().trim()
            var email = bind.ukaEmail.text.toString().trim()

            var mUser = User(id = user.id, name = name, email = email)
            db.userDao.updateRecord(mUser)
            updateList()

            alert.dismiss()

        }
    }

    private fun showDeleteDailog(user: User) {

        AlertDialog.Builder(this)
            .setTitle("Delete Item")
            .setMessage("are you sure want to delete item?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->

                db.userDao.delateRecord(user)
                updateList()

            }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

            }).show()

    }

    private fun insertRecord(name: String, email: String) {

        var user = User(name = name, email = email)
        db.userDao.insertRecord(user)
        Toast.makeText(applicationContext, "Record added", Toast.LENGTH_SHORT).show()

        updateList()

    }

    private fun updateList() {

        var list = db.userDao.getAllRecord() as MutableList<User>
        adapter.setItems(list)

    }
}