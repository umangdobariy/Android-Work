package com.example.bestiu_k24_11_2022interviewpass.listner

import com.example.bestiu_k24_11_2022interviewpass.database.entity.User

interface OnItemClickListner {

    fun OnDelateItem(user: User)
    fun OnUpdateItem(user: User)
}