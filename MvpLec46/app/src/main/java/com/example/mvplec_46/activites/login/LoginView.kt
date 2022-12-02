package com.example.mvplec_46.activites.login

import android.os.Message
import com.example.jsonparshinglec_3536.modal.User

interface LoginView {


    fun success(user: User)
    fun failure(message: String)
}