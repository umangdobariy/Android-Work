package com.example.googleintergration

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class HomeActivity : AppCompatActivity() {

    val tvResult:TextView
    get() = findViewById(R.id.tv_result)

    lateinit var gso: GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto: Uri? = acct.photoUrl

            tvResult.text = """
                personName : $personName
                personGiven : $personGivenName
                personID : $personId
            """.trimIndent()
        }

        findViewById<Button>(R.id.btn_logout).setOnClickListener {

            googleSignInClient.signOut().addOnCompleteListener {
                if (it.isSuccessful){
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    finishAffinity()
                }
            }

        }
    }
}