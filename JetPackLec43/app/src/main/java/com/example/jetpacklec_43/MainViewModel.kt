package com.example.jetpacklec_43

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel (var initialValue:Int) : ViewModel() {

    private var counter = initialValue

    var counterLiveData = MutableLiveData<Int>(counter)
//
//    fun getCounter():Int{
//        return counter
//

    fun increment()
    {
        counterLiveData.value = ++counter
    }

    fun decrement()
    {
        counterLiveData.value = --counter
    }

}