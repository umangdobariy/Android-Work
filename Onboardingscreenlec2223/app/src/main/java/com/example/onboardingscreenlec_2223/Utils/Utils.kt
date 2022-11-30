package com.example.onboardingscreenlec_2223.Utils

import java.text.DecimalFormat
import java.text.NumberFormat

class Utils {

    companion object{

        fun getFormettedNumber(number:Int):String {

            val formatter: NumberFormat = DecimalFormat("00")
            val s: String = formatter.format(number)
            return s

        }
    }

}