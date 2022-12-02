package com.example.interview24_11_2022.listner

import com.example.interview24_11_2022.database.entity.User

interface OnIteemClickListner {

    fun OnDeleteItem(user: User)
    fun OnUpdateItem(user: User)
}