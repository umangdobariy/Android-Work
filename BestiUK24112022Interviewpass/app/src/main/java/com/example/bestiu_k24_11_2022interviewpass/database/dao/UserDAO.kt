package com.example.bestiu_k24_11_2022interviewpass.database.dao

import androidx.room.*
import com.example.bestiu_k24_11_2022interviewpass.database.entity.User

@Dao
interface UserDAO {

    @Insert
    fun insertRecord(user: User)

    @Update
    fun updateRecord(user: User)

    @Delete
    fun delateRecord(user: User)

    @Query("select * from 'user_table'")
    fun getAllRecord():List<User>

    @Query("select * from 'user_table' where id = :Uid")
    fun getUser(Uid:Int) : User
}
