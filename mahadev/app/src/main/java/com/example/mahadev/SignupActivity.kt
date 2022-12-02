package com.example.mahadev

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.provider.ContactsContract
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.mahadev.Utils.Utils
import com.example.mahadev.databinding.ActivitySignupBinding
import java.util.*
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding


    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.udRegister.setOnClickListener {

            var name = binding.udName.text.toString().trim()
            var contact = binding.udContact.text.toString().trim()
            var email = binding.udEmail.text.toString().trim()
            var password = binding.udPassword.text.toString().trim()
            var cpassword = binding.udCpassword.text.toString().trim()
            var dateofbirth = binding.udDate.text.toString().trim()

            resetFocus()

            if (name.isEmpty())
            {
                setError(binding.udName,"Enter your name")
            }else if (!isValidcontact(contact))
            {
                setError(binding.udContact,"Enter your contact")
            }else if (!isvalidemail(email))
            {
                setError(binding.udEmail,"Enter your email")
            }else if (!isValidpassword(password))
            {
                setError(binding.udPassword,"Enter your password")
            }else if (!isvalidcpassword(cpassword))
            {
                setError(binding.udCpassword,"Enter your cpassword")
            }else if (dateofbirth.isEmpty())
            {
                setError(binding.udDate,"Enter your birth of date")
            }else
            {
                startActivity(Intent(this,HomeActivity::class.java))
                Toast.makeText(this, "All fields are validated", Toast.LENGTH_SHORT).show()
            }
        }
        binding.udDate.setOnFocusChangeListener { view, b ->

            if (b){
                binding.udDate.clearFocus()
                pickdob()
            }
        }

    }

    private fun pickdob() {
        var calendar = Calendar.getInstance()
        var date = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH)
        var year = calendar.get(Calendar.YEAR)

        var dialog = DatePickerDialog(
            this,
            { p0, year, month, day ->

                var dob = "${Utils.getFormettedNumber(day)}-${Utils.getFormettedNumber(month+1)}-$year"  //date formate
                binding.udDate.setText(dob)

            },year,month,date)

        dialog.show()
    }

    private fun resetFocus() {
        binding.udName.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udContact.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udEmail.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udPassword.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udCpassword.setBackgroundResource(R.drawable.rd_backgroundshape)
        binding.udDate.setBackgroundResource(R.drawable.rd_backgroundshape)

        binding.udName.clearFocus()
        binding.udContact.clearFocus()
        binding.udEmail.clearFocus()
        binding.udPassword.clearFocus()
        binding.udCpassword.clearFocus()
        binding.udDate.clearFocus()
    }

    private fun setError(editText: EditText?,message: String) {
        editText?.let {
            it.background = ContextCompat.getDrawable(this,R.drawable.ud_error)
            it.requestFocus()
        }
    }

    private fun isValidcontact(contact:String):Boolean
    {
        return contact.length==10
    }
    private fun isvalidemail(email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun  isValidpassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
    private fun  isvalidcpassword(cpassword:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(cpassword).matches()
    }

}