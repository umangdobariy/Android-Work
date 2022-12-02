package com.example.mvplec_46.activites.userlist

import com.example.jsonparshinglec_3536.modal.User

interface MainView {

    fun success(userList:List<User>)
    fun failure(message:String)
    fun showProgress()
    fun hideProgress()
}