package com.example.roomcredoperation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomcredoperation.database.dao.UserDAO
import com.example.roomcredoperation.database.entity.User

@Database(entities = [User::class],version = 1 )
    abstract class Appdatabase :RoomDatabase() {

    abstract val userDao: UserDAO

}