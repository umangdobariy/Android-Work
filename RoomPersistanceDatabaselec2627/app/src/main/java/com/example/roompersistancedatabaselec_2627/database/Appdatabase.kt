package com.example.roompersistancedatabaselec_2627.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roompersistancedatabaselec_2627.database.dao.UserDAO
import com.example.roompersistancedatabaselec_2627.database.entity.User


abstract class Appdatabase : RoomDatabase() {

    abstract val userDao: UserDAO

}