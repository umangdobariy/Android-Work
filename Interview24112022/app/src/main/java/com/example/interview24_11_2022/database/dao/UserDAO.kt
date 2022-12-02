package com.example.interview24_11_2022.database.dao

import androidx.room.*
import com.example.interview24_11_2022.database.entity.User

@Dao
interface UserDAO {

    @Insert
    fun insertRecord(user: User)

    @Update
    fun updateRecord(user: User)

    @Delete
    fun deleteRecord(user: User)

    @Query("select * from 'user_table'")
    fun getAllRecords() : List<User>

    @Query("select * from 'user_table' where id = :Uid")
    fun getUser(Uid:Int):User
}