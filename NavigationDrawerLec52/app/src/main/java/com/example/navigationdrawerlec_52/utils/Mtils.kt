package com.example.navigationdrawerlec_52.utils

import java.text.DecimalFormat
import java.text.NumberFormat

class Mtils {

    companion object{

        fun getFormettedNumber(number:Int):String {

            val formatter: NumberFormat = DecimalFormat("00")
            val s: String = formatter.format(number)
            return s

        }
    }

}