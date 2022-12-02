package com.example.crd

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crd.adapter.RecycleUserAdapter
import com.example.crd.database.Appdatabase
import com.example.crd.database.entity.User
import com.example.crd.databinding.ActivityMainBinding
import com.example.crd.databinding.ItemUserDialogLayoutBinding
import com.example.crd.listener.OnListItemClickListner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var db:Appdatabase
    private lateinit var adapter: RecycleUserAdapter
    var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = Room.databaseBuilder(this,Appdatabase::class.java,"tops-user.db").allowMainThreadQueries().build()

        adapter = RecycleUserAdapter(this,userList)

        var manager = LinearLayoutManager(applicationContext)
        binding.recyclerviewItem.layoutManager = manager

        binding.recyclerviewItem.adapter = adapter

        adapter.setOnListItemClickListner(object : OnListItemClickListner{
            override fun onDeleteItemClicked(user: User) {
                showDeleteDialog(user)
            }

            override fun onUpdateItemClicked(user: User) {
                showUpdateDailog(user)
            }

        })

        updateList()


        binding.uvSave.setOnClickListener {

            var name = binding.uvName.text.toString().trim()
            var email = binding.uvEmail.text.toString().trim()


            insertRecord(name,email)
        }

    }

    private fun showUpdateDailog(user: User) {

        var bind = ItemUserDialogLayoutBinding.inflate(layoutInflater)

        var builder = AlertDialog.Builder(this)
            .setView(bind.root)

        bind.rvName.setText(user.name)
        bind.rvEmail.setText(user.email)


        var alert = builder.create()
        alert.show()

        bind.btnupdate.setOnClickListener {

          var name = bind.rvName.text.toString().trim()
          var email = bind.rvEmail.text.toString().trim()

            var mUser = User(id = user.id, name = name, email = email)

            db.userDao.updateRecord(mUser)
            updateList()
            alert.dismiss()
        }
    }

    private fun showDeleteDialog(user: User) {

        AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("are you sure want to delete this item?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
                db.userDao.deleteRecord(user)
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

        var list = db.userDao.getAllrecords() as MutableList<User>
        adapter.setItems(list)
    }
}