package com.example.splashfragmentlec_8.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splashfragmentlec_8.Fragments.LoginFragment
import com.example.splashfragmentlec_8.R

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