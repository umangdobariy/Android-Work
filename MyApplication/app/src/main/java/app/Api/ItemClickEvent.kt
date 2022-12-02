package app.Api

import app.Api.model.User

interface ItemClickEvent {

    fun onViewClick(pos:Int,user: User)
    fun onItemDelete(user: User)
    fun onItemUpdate(pos: Int,user: User)

}