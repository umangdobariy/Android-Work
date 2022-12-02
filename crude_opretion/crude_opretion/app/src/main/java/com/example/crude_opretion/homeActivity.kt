package com.example.crude_opretion

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crude_opretion.ClickeEvent.onclicklistner
import com.example.crude_opretion.adaptor.rec_Adaptor
import com.example.crude_opretion.databinding.ActivityHomeBinding
import com.example.crude_opretion.databinding.CustomDailogBinding
import com.example.crude_opretion.modal.User
import com.example.crude_opretion.modal.studentlist
import com.example.crude_opretion.network.ApiClient
import com.example.crude_opretion.shareprefrence.prefrence
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class homeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var recAdaptor: rec_Adaptor
    private var reclist = mutableListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set data
        initeData()

        //logout


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
     menuInflater.inflate(R.menu.logout,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {


            R.id.logout->{
                AlertDialog.Builder(this)
                    .setTitle("LogOut")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Logout", DialogInterface.OnClickListener { dialogInterface, i ->
                     var manger = prefrence(this)
                        manger.logout()
                        startActivity(Intent(this,LoginActivity::class.java))


                    }).setNegativeButton("Cancle", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }).show()
                true

            }else->{

            super.onOptionsItemSelected(item)

            }
    }

    private fun initeData() {

        //adddata data get in online
        addData()

        recAdaptor = rec_Adaptor(this, reclist)
        binding.recView.adapter = recAdaptor
        binding.recView.layoutManager = LinearLayoutManager(this)

        //clicke evnet
        recAdaptor.Clickelistner(object : onclicklistner {
            override fun clickeevent(view: User, position: Int) {

                Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()

            }

            override fun update(view: User, position: Int) {
                customdailog(view, position)
            }

            override fun delate(view: User) {
                customdelate(view)
            }

        })


    }

    private fun customdelate(user: User) {
        AlertDialog.Builder(this)
            .setTitle("Delate Recoade")
            .setMessage("Are You Sure you want to delate this recode???")
            .setPositiveButton("Delate", DialogInterface.OnClickListener { dialogInterface, i ->

                ApiClient.Init().delatedata(3,user.id).enqueue(object :Callback<studentlist>{
                    override fun onResponse(
                        call: Call<studentlist>,
                        response: Response<studentlist>
                    ) {
                        if (response.isSuccessful){

                            recAdaptor.delete(user)
                            Toast.makeText(this@homeActivity, "${user.id}", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<studentlist>, t: Throwable) {
                        Toast.makeText(this@homeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })

            }).setNegativeButton("Cancle", DialogInterface.OnClickListener { dialogInterface, i ->


            }).show()


    }

    private fun customdailog(view: User, position: Int) {

        /* var binding: CustomDailogBinding
         var dailog = Dialog(this@homeActivity)
         dailog.requestWindowFeature(Window.FEATURE_NO_TITLE)
         dailog.setCancelable(false)
         binding = CustomDailogBinding.inflate(LayoutInflater.from(this))
         dailog.setContentView(binding.root)
         dailog.window?.setLayout(
             WindowManager.LayoutParams.MATCH_PARENT,
             WindowManager.LayoutParams.WRAP_CONTENT
         )*/

        var bind = CustomDailogBinding.inflate(layoutInflater)
        var builder = AlertDialog.Builder(this)
            .setView(bind.root)


        //auto filledailog box
        bind.etName.setText(view.name)
        bind.etEmail.setText(view.email)
        bind.etContect.setText(view.mobile)


        var dailog = builder.create()
        dailog.show()
        bind.btnUpdate.setOnClickListener {


            var name = bind.etName.text.toString().trim()
            var email = bind.etEmail.text.toString().trim()
            var mobile = bind.etContect.text.toString().trim()

            ApiClient.Init().updatedata(2, view.id, name, email, mobile)
                .enqueue(object : Callback<studentlist> {
                    override fun onResponse(
                        call: Call<studentlist>,
                        response: Response<studentlist>
                    ) {

                        if (response.isSuccessful) {
                            response.body()?.let {

                                var userdata = view.copy(
                                    name = name,
                                    email = email,
                                    mobile = mobile,
                                    pass = ""
                                )
                                recAdaptor.update(userdata, position)
                                Toast.makeText(this@homeActivity, "${view.id}", Toast.LENGTH_SHORT)
                                    .show()


                            }
                        }

                    }

                    override fun onFailure(call: Call<studentlist>, t: Throwable) {
                        Toast.makeText(this@homeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
            dailog.dismiss()


        }
        bind.btnCancle.setOnClickListener {
            dailog.dismiss()
        }


    }






    private fun addData() {
        ApiClient.Init().getstudentlist(4).enqueue(object : Callback<studentlist> {
            override fun onResponse(call: Call<studentlist>, response: Response<studentlist>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recAdaptor.setItem(it.student)

                    }
                }
            }

            override fun onFailure(call: Call<studentlist>, t: Throwable) {
                Toast.makeText(this@homeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }


        })
    }
}