package com.example.nestedrecyclerview.modal

data class Category(
    var id:Int,
    var title: String,
    var movieList:MutableList<SubCategory>

)
