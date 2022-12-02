package com.example.facebookloginlec_4849

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.facebookloginlec_4849.databinding.ActivityHomeBinding
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            LoginManager.getInstance().logOut()
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        var accessToken = AccessToken.getCurrentAccessToken()

        accessToken?.let {

            val request = GraphRequest.newMeRequest(it, object : GraphRequest.GraphJSONObjectCallback {
                override fun onCompleted(jsonObject : JSONObject?, response: GraphResponse?) {

                    Log.d("TAG", "onCompleted: $jsonObject")
                }

            })
            val parameters = Bundle()
            parameters.putString("fields", "id,name,link,picture.type(large),email, birthday")
            request.parameters = parameters
            request.executeAsync()
        }
    }
}
