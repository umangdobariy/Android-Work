package com.example.roomcredoperation.listner

import com.example.roomcredoperation.database.entity.User

interface OnListItemClickListner {

    fun OnDeleteItem(user: User)
    fun OnupdateItem(user: User)
}