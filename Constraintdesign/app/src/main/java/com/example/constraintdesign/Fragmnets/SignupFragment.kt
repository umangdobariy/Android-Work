package com.example.constraintdesign.Fragmnets

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.example.constraintdesign.R
import com.example.constraintdesign.databinding.FragmentSignupBinding
import java.util.*
import java.util.regex.Pattern



class SignupFragment : Fragment() {

     private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    private lateinit var binding: FragmentSignupBinding

    private var gender = ""
    private var isChecked = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.udCheckBox.setOnCheckedChangeListener { compoundButton, status ->
            isChecked = status
        }

        binding.udGender.setOnCheckedChangeListener { radioGroup, i ->

            when(i){
                R.id.ud_male -> gender = "Male"
                R.id.ud_Female -> gender = "Female"
                R.id.ud_other -> gender = "Others"
            }
            Toast.makeText(requireActivity(),"$i",Toast.LENGTH_SHORT).show()
        }

        binding.udDob.setOnFocusChangeListener { view, status ->

            if (status){
                binding.udDob.clearFocus()
                showDatepickerDialog()
            }
        }


        binding.button5.setOnClickListener {
            var name = binding.editTextTextEmailAddress4.text.toString().trim()
            var contact = binding.editTextTextEmailAddress10.text.toString().trim()
            var email = binding.editTextTextEmailAddress9.text.toString().trim()
            var password = binding.editTextTextPassword2.text.toString().trim()
            var cpassword = binding.editTextTextPassword3.text.toString().trim()
            var dob = binding.udDob.text.toString().trim()


            if (name.isEmpty()) {
                binding.editTextTextEmailAddress4.error = "Enter your name"
                Toast.makeText(requireActivity(), "error name", Toast.LENGTH_SHORT).show()
            } else if (!isvalidcontact(contact)) {
                binding.editTextTextEmailAddress10.error = "Enter your contact"
                Toast.makeText(requireActivity(), "error contact", Toast.LENGTH_SHORT).show()
            } else if (!isvalidemail(email)) {
                binding.editTextTextEmailAddress9.error = "Enter your email"
                Toast.makeText(requireActivity(), "error email", Toast.LENGTH_SHORT).show()
            } else if (!isvalidpassword(password)) {
                binding.editTextTextPassword2.error = "Enter your password"
                Toast.makeText(requireActivity(), "error password", Toast.LENGTH_SHORT).show()
            } else if (!isvalidcpassword(cpassword)) {
                binding.editTextTextPassword3.error = "Enter your cpassword"
                Toast.makeText(requireActivity(), "error cpassword", Toast.LENGTH_SHORT).show()
            } else if (dob.isEmpty()) {
                binding.udDob.error = "select your date of birth"
                Toast.makeText(requireActivity(), "select your date of birth", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireActivity(), "All fields are validated", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun showDatepickerDialog() {

        var calendar = Calendar.getInstance()
        var date = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH)
        var year = calendar.get(Calendar.YEAR)

        var dialog = DatePickerDialog(requireActivity(),
            { p0, year, month, day ->

                var dob = "${Utils.getFormettedNumber(day)}-${Utils.getFormettedNumber(month+1)}-$year"  //date formate
                binding.udDob.setText(dob)

            },year,month,date)
            dialog.show()
    }


    private fun isvalidcontact(contact:String):Boolean
    {
        return contact.length==10
    }
    private fun  isvalidemail(email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isvalidpassword(password:String):Boolean
    {
        return  Pattern.compile(REGEX).matcher(password).matches()
    }
    private fun isvalidcpassword(cpssword:String):Boolean
    {
        return  Pattern.compile(REGEX).matcher(cpssword).matches()
    }
}