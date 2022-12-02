package com.example.mvplec_46.activites.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsonparshinglec_3536.modal.User
import com.example.mvplec_46.R

class LoginActivity : AppCompatActivity(),LoginView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var presenter = LoginPresenter()
        presenter.login("12345","abc@gmail.com")
    }

    override fun success(user: User) {
        TODO("Not yet implemented")
    }

    override fun failure(message: String) {
        TODO("Not yet implemented")
    }
}