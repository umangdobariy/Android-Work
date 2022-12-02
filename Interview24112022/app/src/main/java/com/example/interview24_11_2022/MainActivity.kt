package com.example.interview24_11_2022

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.interview24_11_2022.adapter.RecyclerAdapter
import com.example.interview24_11_2022.database.Appdatabase
import com.example.interview24_11_2022.database.entity.User
import com.example.interview24_11_2022.databinding.ActivityMainBinding
import com.example.interview24_11_2022.databinding.CustomDialogLayoutBinding
import com.example.interview24_11_2022.listner.OnIteemClickListner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db:Appdatabase
    private lateinit var adapter:RecyclerAdapter
    var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(this,Appdatabase::class.java,"tops-user.db").allowMainThreadQueries().build()

        adapter = RecyclerAdapter(this,userList)

        var manger = LinearLayoutManager(applicationContext)
        binding.ukRecyclerviewItem.layoutManager = manger
        binding.ukRecyclerviewItem.adapter = adapter

        adapter.setOnClickListner(object :OnIteemClickListner{
            override fun OnDeleteItem(user: User) {
                showDelateDailog(user)
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

        var bind = CustomDialogLayoutBinding.inflate(layoutInflater)

        var builder = AlertDialog.Builder(this)
            .setView(bind.root)

        bind.rvName.setText(user.name)
        bind.rvEmail.setText(user.email)

        var alert = builder.create()
        alert.dismiss()

        bind.btnupdate.setOnClickListener {

            var name = bind.rvName.toString().trim()
            var email = bind.rvEmail.toString().trim()

            var mUser = User(id = user.id, name = name, email = email)

            db.userdao.updateRecord(mUser)
            updateList()

            alert.dismiss()
        }

    }

    private fun showDelateDailog(user: User) {

        AlertDialog.Builder(this)
            .setTitle("Delete record")
            .setMessage("are you sure want to delete this item?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
                db.userdao.deleteRecord(user)
                updateList()
            }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

            }).show()
    }

    private fun insertRecord(name: String, email: String) {

        var user = User(name = name, email = email)

        db.userdao.insertRecord(user)
        Toast.makeText(applicationContext, "Record added", Toast.LENGTH_SHORT).show()

        updateList()

    }

    private fun updateList() {

        var list = db.userdao.getAllRecords() as MutableList<User>
        adapter.setItems(list)

    }
}