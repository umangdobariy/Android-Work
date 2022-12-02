package com.example.jaybajrangbali.model

data class Category(

    var id:Int,
    var title:String,
    var foodList: MutableList<Subcategory>
)
