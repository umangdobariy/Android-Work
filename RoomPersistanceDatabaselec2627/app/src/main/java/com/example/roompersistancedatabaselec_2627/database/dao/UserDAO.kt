package com.example.roompersistancedatabaselec_2627.database.dao

import androidx.room.*
import com.example.roompersistancedatabaselec_2627.database.entity.User

@Dao
interface UserDAO {

    @Insert
    fun insertRecord(user: User)

//    @Query("select * from `user_table`")
//    fun getUserList():List<User>

    @Query("select * from `user_table`")
    fun getUserList():List<User>


    @Query("select * from `user_table` where id = :Uid")
    fun getUser(Uid:Int):User

//    @Query("delete  from user_table where id =  id")
//    fun deleteRecord(id:Int)

    @Delete
    fun delete(user: User)

    @Update
    fun updateRecord(updateUser:User)
}