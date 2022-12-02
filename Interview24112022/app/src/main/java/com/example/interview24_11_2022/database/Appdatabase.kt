package com.example.interview24_11_2022.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.interview24_11_2022.database.dao.UserDAO
import com.example.interview24_11_2022.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class Appdatabase : RoomDatabase() {

    abstract val userdao:UserDAO
}