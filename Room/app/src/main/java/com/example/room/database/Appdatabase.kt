package com.example.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.database.dao.userDAO
import com.example.room.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class Appdatabase : RoomDatabase() {

    abstract val userDao:userDAO
}