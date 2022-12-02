package com.example.timepicker

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity(){

    val btnCalendar:ImageView
    get() = findViewById(R.id.iv_date)

    val ettime:EditText
    get() = findViewById(R.id.et_time)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalendar.setOnClickListener {
            pickdob()
        }
    }

    private fun pickdob() {
        var calendar = Calendar.getInstance()
        var mHour = calendar.get(Calendar.HOUR)
        var mMinute = calendar.get(Calendar.MINUTE)

        var dialog= TimePickerDialog(this,object :TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(p0: TimePicker?, mHour: Int,mMinute: Int) {

                var cleHour = if((mHour)<10) "0${mHour}" else mHour
                var cleMinute = if(mMinute<10) "0${mMinute}" else mMinute


                ettime.setText("$cleHour:$cleMinute")
            }

        },mHour,mMinute,true)
        dialog.show()
    }
}