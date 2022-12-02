package com.example.crd.listener

import com.example.crd.database.entity.User

interface OnListItemClickListner {

    fun onDeleteItemClicked(user: User)
    fun onUpdateItemClicked(user: User)
}