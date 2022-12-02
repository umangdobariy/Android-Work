package com.example.xampplocal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitnetwork.database.ApiClient
import com.example.xampplocal.adapter.DeletedItemAdapter
import com.example.xampplocal.adapter.UserAdapter
import com.example.xampplocal.databinding.ActivityDeletedItemBinding
import com.example.xampplocal.model.Student
import com.example.xampplocal.model.User
import com.example.xampplocal.sharepref.PrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeletedItemActivity : AppCompatActivity() {

    lateinit var binding:ActivityDeletedItemBinding
    lateinit var adapter: DeletedItemAdapter
    var list = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletedItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadData()
        adapter = DeletedItemAdapter(this, list)
        binding.rcvDeleteUser.layoutManager  = LinearLayoutManager(this)
        binding.rcvDeleteUser.adapter = adapter


        adapter.onItemSelected(object: DeletedItemAdapter.ItemClickListner{
            override fun onUndoButtonClicked(pos: Int, user: User) {
                //active flag 1-remove from bin-add to home
                ApiClient.init().undoUserData(8,user.id).enqueue(object:Callback<Student>{
                    override fun onResponse(call: Call<Student>, response: Response<Student>) {
                       if(response.isSuccessful){
                           response.body()?.let {
                               adapter.deleteData(user)
                           }
                       }
                    }

                    override fun onFailure(call: Call<Student>, t: Throwable) {

                    }

                })

            }

            override fun onDeleteButtonClicked(user: User) {
                AlertDialog.Builder(this@DeletedItemActivity)
                    .setTitle("Delete")
                    .setMessage("are you sure you want to delete the data?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->
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

                    }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }).show()
            }

        })

    }

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE

        ApiClient.init().getUserData(7).enqueue(object: Callback<Student> {
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


}