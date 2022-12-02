package app.Api

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import app.Api.adapter.UserAdapter
import app.Api.databinding.ActivityHomeBinding
import app.Api.databinding.CustomdilogBinding
import app.Api.model.StudentData
import app.Api.model.User
import app.Api.network.ApiClient
import app.Api.pref.prefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var adapter: UserAdapter
    var listData = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    override fun onResume() {
        super.onResume()
        addData()

    }

    fun init(){

        addData()

        adapter = UserAdapter(this, listData)
        binding.rvListData.layoutManager = LinearLayoutManager(this)
        binding.rvListData.adapter = adapter


        adapter.onItemSeclected(object : ItemClickEvent{
            override fun onViewClick(pos: Int, user: User) {
                TODO("Not yet implemented")
            }

            override fun onItemDelete(user: User) {
                ApiClient.init().DeleteUser(3,user.id)
                    .enqueue(object : Callback<StudentData>{
                        override fun onResponse(
                            call: Call<StudentData>,
                            response: Response<StudentData>
                        ) {

                            if (response.isSuccessful){
                                response.body()?.let {
                                    adapter.delete(user)
                                    Toast.makeText(this@HomeActivity, "${user.id}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<StudentData>, t: Throwable) {
                            Toast.makeText(this@HomeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }

                    })

            }

            override fun onItemUpdate(pos: Int, user: User) {
                    customDilog(pos,user)
            }

        })

    }
    private fun addData() {


        ApiClient.init().getUserData(4).enqueue(object :retrofit2.Callback<StudentData>{
            override fun onResponse(call: Call<StudentData>, response: Response<StudentData>) {

                if (response.isSuccessful){
                    response.body()?.let {
                            adapter.setItem(it.student)
                    }
                }
            }

            override fun onFailure(call: Call<StudentData>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
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


    fun customDilog(pos:Int,user: User){

        var binding:CustomdilogBinding

        var dialog = Dialog(this@HomeActivity)
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
                                Toast.makeText(this@HomeActivity, "${userdata.id}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<StudentData>, t: Throwable) {
                        Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_SHORT).show()
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