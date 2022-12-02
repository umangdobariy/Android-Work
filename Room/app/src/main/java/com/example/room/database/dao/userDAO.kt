package com.example.room.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room.database.entity.User

interface userDAO {

    @Insert
    fun insertRecord(user: User)

    @Update
    fun updateRecord(user: User)

    @Delete
    fun deleteRecord(user: User)


    @Query("select * from 'user_table'")
    fun getAllRecords() : List<User>

    @Query("select * from  'user_table' where id = :Uid")
    fun getUser(Uid:Int):User

}