package com.example.bestiu_k24_11_2022interviewpass.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bestiu_k24_11_2022interviewpass.database.dao.UserDAO
import com.example.bestiu_k24_11_2022interviewpass.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class Appdatabase: RoomDatabase() {

    abstract val userDao:UserDAO
}