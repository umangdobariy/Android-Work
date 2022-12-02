package com.example.singlehotel.activityimport


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.viewpager.widget.ViewPager
import com.example.singlehotel.R

import com.example.singlehotel.util.*
import com.facebook.CallbackManager.Factory.create
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BookRoom : AppCompatActivity() {
    private var method: Method? = null
    private var toolbar: MaterialToolbar? = null
    private var isDate = false
    private var button: MaterialButton? = null
    private var imm: InputMethodManager? = null
    private var progressDialog: ProgressDialog? = null
    private var numberAdults: Array<String>
    private var numberChildren: Array<String>
    private var datePickerDialog: DatePickerDialog? = null
    private var textViewArrivalDate: MaterialTextView? = null
    private var textViewDepartureDate: MaterialTextView? = null
    private var spinnerAdults: AppCompatSpinner? = null
    private var spinnerChildren: AppCompatSpinner? = null
    private var editTextName: TextInputEditText? = null
    private var editTextEmail: TextInputEditText? = null
    private var editTextPhoneNo: TextInputEditText? = null
    private var year = 0
    private var month = 0
    private var day = 0
    private var arrYear = 0
    private var arrMonth = 0
    private var arrDay = 0
    private var roomId: String? = null
    private var adults: String? = null
    private var children: String? = null
    private var arrivalDate: String? = null
    private var departureDate: String? = null
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_room)
        method = Method(this@BookRoom)
        method!!.forceRTLIfSupported()
        roomId = intent.getStringExtra("id")
        progressDialog = ProgressDialog(this@BookRoom)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val c = Calendar.getInstance()
        year = c[Calendar.YEAR]
        month = c[Calendar.MONTH]
        day = c[Calendar.DAY_OF_MONTH]
        numberAdults = arrayOf(
            resources.getString(R.string.adults),
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
        numberChildren = arrayOf(
            resources.getString(R.string.children),
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
        toolbar = findViewById(R.id.toolbar_book_room)
        toolbar.setTitle(resources.getString(R.string.book_now))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        editTextName = findViewById(R.id.editText_name_book_room)
        editTextEmail = findViewById(R.id.editText_email_book_room)
        editTextPhoneNo = findViewById(R.id.editText_phoneNo_book_room)
        spinnerAdults = findViewById(R.id.spinner_adults_book_room)
        spinnerChildren = findViewById(R.id.spinner_children_book_room)
        textViewArrivalDate = findViewById(R.id.textView_arrivalDate_booking)
        textViewDepartureDate = findViewById(R.id.textView_departureDate_booking)
        button = findViewById(R.id.button_book_room)
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout_book_room)
        method!!.showBannerAd(linearLayout)
        spinnerAdults.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    (parent.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.textView_book_room))
                } else {
                    (parent.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.textView_app_color))
                }
                adults = numberAdults[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        spinnerChildren.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    (parent.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.textView_book_room))
                } else {
                    (parent.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.textView_app_color))
                }
                children = numberChildren[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        textViewArrivalDate.setOnClickListener(View.OnClickListener { v: View? ->
            datePickerDialog = DatePickerDialog(
                this@BookRoom,
                R.style.datePicker,
                { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                    arrYear = year
                    arrMonth = month
                    arrDay = dayOfMonth
                    isDate = true
                    arrivalDate = selectDate(dayOfMonth, month, year)
                    textViewArrivalDate.setText(arrivalDate)
                },
                year,
                month,
                day
            )
            datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog!!.show()
        })
        textViewDepartureDate.setOnClickListener(View.OnClickListener { v: View? ->
            val calendar = Calendar.getInstance()
            calendar[arrYear, arrMonth] = arrDay
            val startTime = calendar.timeInMillis
            if (isDate) {
                datePickerDialog = DatePickerDialog(
                    this@BookRoom,
                    R.style.datePicker,
                    { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                        departureDate = selectDate(dayOfMonth, month, year)
                        textViewDepartureDate.setText(departureDate)
                    },
                    arrYear,
                    arrMonth,
                    arrDay + 1
                )
                datePickerDialog!!.datePicker.minDate = startTime - 1000
                datePickerDialog!!.show()
            } else {
                method!!.alertBox(resources.getString(R.string.please_select_first_arrivalDate))
            }
        })
        button.setOnClickListener(View.OnClickListener { v: View? -> submit() })
        adultsSpinner()
        childrenSpinner()
        if (method!!.isNetworkAvailable) {
            if (method!!.isLogin) {
                profile(method!!.userId())
            }
        } else {
            method!!.alertBox(resources.getString(R.string.internet_connection))
        }
    }

    fun selectDate(day: Int, month: Int, year: Int): String {
        val monthYear: String
        val dayMonth: String
        monthYear = if (month + 1 < 10) {
            "0" + (month + 1)
        } else {
            (month + 1).toString()
        }
        dayMonth = if (day < 10) {
            "0$day"
        } else {
            day.toString()
        }
        return "$dayMonth/$monthYear/$year"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun adultsSpinner() {
        val adults: List<String> = ArrayList() // Spinner Drop down elements
        Collections.addAll(adults, *numberAdults)
        val dataAdapter = ArrayAdapter(
            this@BookRoom,
            android.R.layout.simple_spinner_item,
            adults
        ) // Creating adapter for spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Drop down layout style - list view with radio button
        spinnerAdults!!.adapter = dataAdapter // attaching data adapter to spinner
    }

    fun childrenSpinner() {
        val children: List<String> = ArrayList() // Spinner Drop down elements
        Collections.addAll(children, *numberChildren)
        val dataAdapterChildren = ArrayAdapter(
            this@BookRoom,
            android.R.layout.simple_spinner_item,
            children
        ) // Creating adapter for spinner
        dataAdapterChildren.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Drop down layout style - list view with radio button
        spinnerChildren!!.adapter = dataAdapterChildren // attaching data adapter to spinner
    }

    private fun isValidMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun submit() {
        val name = editTextName!!.text.toString()
        val email = editTextEmail!!.text.toString()
        val phoneNo = editTextPhoneNo!!.text.toString()
        if (name == "" || name.isEmpty()) {
            editTextName!!.requestFocus()
            editTextName!!.error = resources.getString(R.string.please_enter_name)
        } else if (!isValidMail(email) || email.isEmpty()) {
            editTextEmail!!.requestFocus()
            editTextEmail!!.error = resources.getString(R.string.please_enter_email)
        } else if (phoneNo == "" || phoneNo.isEmpty()) {
            editTextPhoneNo!!.requestFocus()
            editTextPhoneNo!!.error = resources.getString(R.string.please_enter_name)
        } else if (adults == resources.getString(R.string.adults) || adults == "") {
            method!!.alertBox(resources.getString(R.string.please_select_adults))
        } else if (children == resources.getString(R.string.children) || children == "") {
            method!!.alertBox(resources.getString(R.string.please_select_children))
        } else if (arrivalDate == null || arrivalDate == "") {
            method!!.alertBox(resources.getString(R.string.please_select_arrivalDate))
        } else if (departureDate == null || departureDate == "") {
            method!!.alertBox(resources.getString(R.string.please_select_departureDate))
        } else if (adults == "0" && children == "0") {
            method!!.alertBox(resources.getString(R.string.please_select_adults_children))
        } else {
            editTextName!!.clearFocus()
            editTextEmail!!.clearFocus()
            editTextPhoneNo!!.clearFocus()
            imm!!.hideSoftInputFromWindow(editTextName!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextEmail!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextPhoneNo!!.windowToken, 0)
            if (method!!.isNetworkAvailable) {
                booking(
                    name,
                    email,
                    phoneNo,
                    roomId,
                    adults,
                    children,
                    arrivalDate!!,
                    departureDate!!
                )
            }
        }
    }

    fun profile(userId: String?) {
        progressDialog!!.show()
        progressDialog!!.setMessage(resources.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
        val jsObj = Gson().toJsonTree(API(this@BookRoom)) as JsonObject
        jsObj.addProperty("user_id", userId)
        jsObj.addProperty("method_name", "user_profile")
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.getProfile(API.Companion.toBase64(jsObj.toString()))
        call.enqueue(object : Callback<ProfileRP?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ProfileRP?>, response: Response<ProfileRP?>) {
                val statusCode = response.code()
                try {
                    val profileRP = response.body()!!
                    if (profileRP.status == "1") {
                        if (profileRP.success == "1") {
                            editTextEmail!!.setText(profileRP.email)
                            editTextName!!.setText(profileRP.name)
                            editTextPhoneNo!!.setText(profileRP.phone)
                        }
                    } else if (profileRP.status == "2") {
                        method!!.suspend(profileRP.message)
                    }
                } catch (e: Exception) {
                    Log.d("exception_error", e.toString())
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
                progressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<ProfileRP?>, t: Throwable) {
                // Log error here since request failed
                Log.e("fail", t.toString())
                progressDialog!!.dismiss()
                method!!.alertBox(resources.getString(R.string.failed_try_again))
            }
        })
    }

    private fun booking(
        name: String,
        email: String,
        phoneNo: String,
        roomId: String?,
        adults: String?,
        children: String?,
        arrivalDate: String,
        departureDate: String
    ) {
        progressDialog!!.show()
        progressDialog!!.setMessage(resources.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
        val jsObj = Gson().toJsonTree(API(this@BookRoom)) as JsonObject
        jsObj.addProperty("name", name)
        jsObj.addProperty("email", email)
        jsObj.addProperty("phone", phoneNo)
        jsObj.addProperty("room_id", roomId)
        jsObj.addProperty("adults", adults)
        jsObj.addProperty("children", children)
        jsObj.addProperty("check_in_date", arrivalDate)
        jsObj.addProperty("check_out_date", departureDate)
        jsObj.addProperty("method_name", "get_room_booking")
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.roomBooking(API.Companion.toBase64(jsObj.toString()))
        call.enqueue(object : Callback<BookingRoomRP?> {
            override fun onResponse(
                call: Call<BookingRoomRP?>,
                response: Response<BookingRoomRP?>
            ) {
                val statusCode = response.code()
                try {
                    val bookingRoomRP = response.body()!!
                    if (bookingRoomRP.status == "1") {
                        if (bookingRoomRP.success == "1") {
                            bookingDialog(bookingRoomRP.msg)
                        } else {
                            method!!.alertBox(bookingRoomRP.msg)
                        }
                    } else {
                        method!!.alertBox(bookingRoomRP.message)
                    }
                } catch (e: Exception) {
                    Log.d("exception_error", e.toString())
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
                progressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<BookingRoomRP?>, t: Throwable) {
                // Log error here since request failed
                Log.e("fail", t.toString())
                progressDialog!!.dismiss()
                method!!.alertBox(resources.getString(R.string.failed_try_again))
            }
        })
    }

    private fun bookingDialog(message: String) {
        val dialog = Dialog(this@BookRoom)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_booking)
        dialog.setCancelable(false)
        if (method!!.isRtl) {
            dialog.window!!.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.setLayout(
            ViewPager.LayoutParams.MATCH_PARENT,
            ViewPager.LayoutParams.WRAP_CONTENT
        )
        val textView_message =
            dialog.findViewById<MaterialTextView>(R.id.textView_message_dialog_booking)
        val button = dialog.findViewById<MaterialButton>(R.id.button_dialog_booking)
        textView_message.text = message
        button.setOnClickListener { v: View? ->
            dialog.dismiss()
            startActivity(Intent(this@BookRoom, MainActivity::class.java))
            finishAffinity()
        }
        dialog.show()
    }
}