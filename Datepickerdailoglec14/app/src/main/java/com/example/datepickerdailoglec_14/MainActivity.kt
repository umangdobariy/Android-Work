package com.example.datepickerdailoglec_14

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import java.util.*


class MainActivity : AppCompatActivity() {


    private val btnCalender:ImageView
    get() = findViewById(R.id.iv_date)

    val etDob: EditText    // GET karva  mate
    get() = findViewById(R.id.et_dob)

    var dob:String = "" //user kay select nathi thyu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCalender.setOnClickListener {
            pickDob()
        }
    }

    private fun pickDob() {

        var calender = Calendar.getInstance()
        var mYear = calender.get(Calendar.YEAR)
        var mMonth = calender.get(Calendar.MONTH)
        var mDayOfMonth = calender.get(Calendar.DAY_OF_MONTH)


        val dialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                var tempMonth = if((month+1)<10) "0${month+1}" else month+1
                var tempDay = if(dayOfMonth<10) "0${dayOfMonth}" else dayOfMonth

//                dob = "$dayOfMonth/$month/$year"
//                etDob.setText(dob)//SET KARVA

                dob = "$tempDay/${tempMonth}/$year"
                etDob.setText(dob)
            }

        },mYear,mMonth,mDayOfMonth)
        dialog.show()
    }
}