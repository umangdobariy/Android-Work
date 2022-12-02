package com.example.crd.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.crd.database.dao.UserDAO
import com.example.crd.database.entity.User

@Database(entities = [User::class],version = 1 )
abstract class Appdatabase: RoomDatabase() {

    abstract val userDao : UserDAO
}