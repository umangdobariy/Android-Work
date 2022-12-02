package com.example.interview24_11_2022.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String,
    var email:String
)
