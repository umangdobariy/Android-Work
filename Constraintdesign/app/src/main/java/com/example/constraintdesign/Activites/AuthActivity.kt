package com.example.constraintdesign.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.constraintdesign.Fragmnets.LoginFragment
import com.example.constraintdesign.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


        // add login fragment to container
        var fragment = LoginFragment()
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.commit()



    }
}