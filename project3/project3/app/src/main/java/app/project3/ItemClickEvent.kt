package app.project3

import app.project3.model.User

interface ItemClickEvent {

    fun onViewClick(pos:Int,user: User)
    fun onItemDelete(user: User)
    fun onItemUpdate(pos: Int,user: User)
}