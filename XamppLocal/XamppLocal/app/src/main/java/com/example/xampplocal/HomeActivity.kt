package com.example.xampplocal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitnetwork.database.ApiClient
import com.example.xampplocal.adapter.UserAdapter
import com.example.xampplocal.callback.RegisterCallbackResponse
import com.example.xampplocal.databinding.ActivityHomeBinding
import com.example.xampplocal.databinding.DialogLayoutBinding
import com.example.xampplocal.model.Student
import com.example.xampplocal.model.User
import com.example.xampplocal.sharepref.PrefManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//recycler bin,soft update
class HomeActivity : AppCompatActivity() {

    lateinit var adapter:UserAdapter
    lateinit var binding:ActivityHomeBinding
    var list = mutableListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


       // PrefManager(this).setLoginStatus(true)

        loadData()
        adapter = UserAdapter(this, list)
        binding.rcvUser.layoutManager  = LinearLayoutManager(this)
        binding.rcvUser.adapter = adapter

        adapter.onItemSelected(object:UserAdapter.ItemClickListner{
            override fun onViewClicked(pos: Int, user: User) {
                var intent = Intent(applicationContext,FinalOutputActivity::class.java)
                intent.putExtra("USER",user)
                startActivity(intent)
            }

            override fun onUpdateButtonClicked(pos: Int, user: User) {
                var bind = DialogLayoutBinding.inflate(layoutInflater)
                var dialog = AlertDialog.Builder(this@HomeActivity)
                    .setView(bind.root)
                    .show()

                bind.etSname.setText(user.name)
                bind.etContact.setText(user.contact)

                bind.btnUpdate.setOnClickListener {
                    dialog.dismiss()
                    var name = bind.etSname.text.toString().trim()
                    var contact = bind.etContact.text.toString().trim()

                    if(name.isEmpty()){
                        bind.etSname.error = "enter the valid name"
                    }else if(contact.isEmpty()){
                        bind.etContact.error = "enter the valid contact"
                    }else {
                        AlertDialog.Builder(this@HomeActivity)
                            .setTitle("Update")
                            .setMessage("are you sure you want to update the data?")
                            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->
                                ApiClient.init().updateUserData(3, user.id,name,contact).enqueue(object :
                                    Callback<Student> {
                                    override fun onResponse(call: Call<Student>, response: Response<Student>
                                    ) {
                                        if (response.isSuccessful) {
                                            response.body()?.let {
                                                var mUser = user.copy(name = name, contact = contact)
                                                adapter.updateRecord(mUser, pos)
                                                Toast.makeText(applicationContext, "Update successfully..", Toast.LENGTH_SHORT).show()
                                            }

                                        }
                                    }
                                    override fun onFailure(call: Call<Student>, t: Throwable) {
                                       Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
                                    }
                                })
                            })
                            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                                    dialog.dismiss()
                                }).show()
                    }
                }
            }

            override fun onDeleteButtonClicked(user: User) {
             /*   AlertDialog.Builder(this@HomeActivity)
                    .setTitle("Delete")
                    .setMessage("are you sure you want to delete the data?")*/
                   // .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog , i ->
                        ApiClient.init().deleteUserData(3,user.id).enqueue(object:
                            Callback<Student>{
                            override fun onResponse(
                                call: Call<Student>,
                                response: Response<Student>
                            ) {
                                if(response.isSuccessful){

                                    response.body()?.let {
                                        adapter.deleteData(user)
                                    }

                                }
                            }
                            override fun onFailure(call: Call<Student>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                            }
                        })

                   /* }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }).show()
*/
            }

        })

    }

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE

        ApiClient.init().getUserData(4).enqueue(object:Callback<Student>{
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                binding.progressBar.visibility = View.GONE
               if(response.isSuccessful){
                   response.body()?.let {
                       adapter.setItem(it.student)

                   }
               }
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.tool_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.logout -> {

                AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to logout?")
                    .setTitle("Logout")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        PrefManager(this).setLoginStatus(false)
                        startActivity(Intent(this, LoginActivity::class.java))
                        Toast.makeText(applicationContext, "Logout Successfully", Toast.LENGTH_SHORT).show()
                        finish()

                    }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }).show()
               true
            }
            R.id.bin->{
               startActivity(Intent(this,DeletedItemActivity::class.java))
                true
            }

            else -> return false
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
        //adapter.setItem(list)
    }
}
