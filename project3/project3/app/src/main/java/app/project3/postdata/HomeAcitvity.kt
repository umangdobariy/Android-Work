package app.project3.postdata

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import app.project3.Adapter.UserAdapter
import app.project3.CallBack.RegisterCallBackResponse
import app.project3.ItemClickEvent
import app.project3.R
import app.project3.databinding.ActivityHomeAcitvityBinding
import app.project3.databinding.CustomdilogBinding
import app.project3.model.StudentData
import app.project3.model.User
import app.project3.network.ApiClient
import app.project3.pref.prefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAcitvity : AppCompatActivity() {

    lateinit var binding: ActivityHomeAcitvityBinding
    lateinit var adapter: UserAdapter
    var listData = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAcitvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    override fun onResume() {
        super.onResume()
        addData()
    }

    fun init() {

//        var manager=prefManager(this)
//
//        manager.getUser()?.let {
//                binding.tvTitle.text="""
//                    id:${it.id}
//                    name:${it.name}
//                    email:${it.email}
//                    contect:${it.contect}
//
//                """.trimIndent()
//        }


        addData()

        adapter = UserAdapter(this, listData)
        binding.rvListData.layoutManager = LinearLayoutManager(this)
        binding.rvListData.adapter = adapter

        //binding.rvListData.addOnScrollListener(object : OnItemSelectedListener)

        adapter.onItemSeectedListner(object : ItemClickEvent {
            override fun onViewClick(pos: Int, user: User) {
            }

            override fun onItemDelete(user: User) {
                ApiClient.init().DeleteUser(3, user.id)
                    .enqueue(object : Callback<StudentData> {
                        override fun onResponse(
                            call: Call<StudentData>,
                            response: Response<StudentData>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    adapter.delete(user)
                                    Toast.makeText(
                                        this@HomeAcitvity,
                                        "${user.id}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<StudentData>, t: Throwable) {
                            Toast.makeText(this@HomeAcitvity, "$t.message", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
            }

            override fun onItemUpdate(pos: Int, user: User) {
                customDilog(pos, user)
            }

        })
    }

    fun addData() {

        ApiClient.init().getUserData(4)
            .enqueue(object : Callback<StudentData> {
                override fun onResponse(call: Call<StudentData>, response: Response<StudentData>) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            adapter.setItem(it.student)
                        }
                    }
                }

                override fun onFailure(call: Call<StudentData>, t: Throwable) {
                    Toast.makeText(this@HomeAcitvity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.item_logout, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_Logout -> {

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Do you want to logout ?")
                    .setTitle("Logout")
                    .setCancelable(false)
                    .setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                        prefManager(this).isLoginStatus(false)
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

                val alert = dialogBuilder.create()
                alert.show()
                true
            }
            else -> return false
        }

    }

    fun customDilog(pos: Int, user: User) {

        var binding: CustomdilogBinding

        var dialog = Dialog(this@HomeAcitvity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        binding = CustomdilogBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        binding.btSubmit.setOnClickListener {

            var name=binding.etName.text.toString().trim()
            var email=binding.etEmail.text.toString().trim()
            var contect=binding.etContect.text.toString().trim()

            ApiClient.init().UpdateData(2,user.id,name,email,contect)
                .enqueue(object : Callback<StudentData>{
                    override fun onResponse(
                        call: Call<StudentData>,
                        response: Response<StudentData>
                    ) {
                        if(response.isSuccessful){
                            var res=response.body()

                            res?.let {

                                var userdata=user.copy(name=name, email = email, contect = contect)
                                adapter.updatedata(pos,userdata)
                                Toast.makeText(this@HomeAcitvity, "${userdata.id}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<StudentData>, t: Throwable) {
                        Toast.makeText(this@HomeAcitvity, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
            dialog.dismiss()
        }
        binding.btCancle.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}