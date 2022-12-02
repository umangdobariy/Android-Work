package com.example.crude_opretion.ClickeEvent

import android.view.View
import com.example.crude_opretion.modal.User

interface onclicklistner {

    fun clickeevent(view:User,position:Int)
    fun update(view: User,position: Int)
    fun delate(view: User)

}