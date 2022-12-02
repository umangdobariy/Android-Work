package com.example.facebookloginlec_48

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult


class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create()

       findViewById<Button>(R.id.btn_facebook).setOnClickListener {


           LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
       }

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }

            override fun onSuccess(result: LoginResult) {
                result.let {
                    navigateToHome()
                }
            }

        })
    }

    private fun navigateToHome() {
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

    override fun onResume() {
        super.onResume()


        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        if (isLoggedIn)
            navigateToHome()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}