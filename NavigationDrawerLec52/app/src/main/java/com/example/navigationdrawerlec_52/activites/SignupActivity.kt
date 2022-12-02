package com.example.navigationdrawerlec_52.activites

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.navigationdrawerlec_52.R
import com.example.navigationdrawerlec_52.databinding.ActivitySignupBinding
import com.example.navigationdrawerlec_52.sharepref.SharePref
import com.example.navigationdrawerlec_52.utils.Mtils
import java.util.*
import java.util.regex.Pattern


class SignupActivity : AppCompatActivity() {


    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.udRegister.setOnClickListener {

            var Name = binding.udName.text.toString().trim()
            var Contact = binding.udContact.text.toString().trim()
            var Dateofbirth = binding.udBirth.text.toString().trim()
            var Email = binding.udEmail.text.toString().trim()
            var Password = binding.udPassword.text.toString().trim()
            var Cpassword = binding.udCpassword.text.toString().trim()


            resetFocus()
            if (Name.isEmpty())
            {
//                binding.udName.error = "Enter your name"
//                Toast.makeText(this,"error name",Toast.LENGTH_SHORT).show()
                setError(binding.udName,"Enter your name")
            }else if (!isValidContact(Contact))
            {
//                binding.udContact.error = "Enter you Contact"
//                Toast.makeText(this,"error Contact",Toast.LENGTH_SHORT).show()
                setError(binding.udContact,"Enter you Contact")
            }
            else if (Dateofbirth.isEmpty())
            {
                //     binding.udBirth.error = "Enter your Birth"
                // Toast.makeText(this, "error Birth", Toast.LENGTH_SHORT).show()
                setError(binding.udBirth,"Enter you Date of Birth")
            }
            else if (!isValidEmail(Email))
            {
//                binding.udEmail.error = "Enter your Email"
//                Toast.makeText(this, "error Email", Toast.LENGTH_SHORT).show()
                setError(binding.udEmail,"Enter you Email")
            }else if (!isValidPassword(Password))
            {
//               binding.udPassword.error = "Enter your password"
//                Toast.makeText(this, "error password", Toast.LENGTH_SHORT).show()
                setError(binding.udPassword,"Enter you Password")

            }else if (!isValidCpassword(Cpassword))
            {
//                binding.udCpassword.error = "Enter Your Cpassword"
//                Toast.makeText(this, "error cpassword", Toast.LENGTH_SHORT).show()
                setError(binding.udCpassword,"Enter you Cpassword")

            }else{

                var prefManagr = SharePref(this)
                prefManagr.setLoginStatus(true)
                startActivity(Intent(this,MainActivity::class.java))
                Toast.makeText(this, "ALL fields are validated", Toast.LENGTH_SHORT).show()
            }

        }

        binding.udBirth.setOnFocusChangeListener { view, b ->

            if(b){
                binding.udBirth.clearFocus()
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

                var dob = "${Mtils.getFormettedNumber(day)}-${Mtils.getFormettedNumber(month+1)}-$year"  //date formate
                binding.udBirth.setText(dob)

            },year,month,date)

        dialog.show()
    }

    private fun resetFocus() {

        binding.udName.setBackgroundResource(R.drawable.ud_background)
        binding.udContact.setBackgroundResource(R.drawable.ud_background)
        binding.udBirth.setBackgroundResource(R.drawable.ud_background)
        binding.udEmail.setBackgroundResource(R.drawable.ud_background)
        binding.udPassword.setBackgroundResource(R.drawable.ud_background)
        binding.udCpassword.setBackgroundResource(R.drawable.ud_background)

        binding.udName.clearFocus()
        binding.udContact.clearFocus()
        binding.udBirth.clearFocus()
        binding.udEmail.clearFocus()
        binding.udPassword.clearFocus()
        binding.udCpassword.clearFocus()


    }

    private fun setError(editText: EditText?, message: String) {

        editText?.let {
            it.background = ContextCompat.getDrawable(this, R.drawable.et_error)
            it.requestFocus()
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isValidContact(Contact:String):Boolean
    {
        return Contact.length==10
    }
    private fun isValidEmail(Email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(Email).matches()
    }
    private fun isValidPassword(Password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(Password).matches()
    }
    private fun isValidCpassword(Cpassowrd:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(Cpassowrd).matches()
    }
}