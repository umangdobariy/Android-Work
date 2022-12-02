package com.example.googleintergration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class MainActivity : AppCompatActivity() {

    lateinit var gso:GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient

    val RC_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1012999053259-9ga9rb5ms28lq7e01ujo1num7hlqh62o.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        findViewById<Button>(R.id.btn_google).setOnClickListener {


            val signInIntent:Intent = googleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode === RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            navigateToHome(account)
            Log.d("TAG", "handleSignInResult: ${account.idToken}")
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
            Toast.makeText(applicationContext, "${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToHome(account: GoogleSignInAccount) {
        startActivity(Intent(applicationContext,HomeActivity::class.java))
    }

    override fun onResume() {
        super.onResume()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account!=null)
            navigateToHome(account)
    }
}