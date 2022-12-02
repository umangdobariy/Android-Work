package com.example.food.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.food.R
import com.example.food.databinding.FragmentSigninBinding
import com.google.android.material.textfield.TextInputLayout


class signinFragment : Fragment() {
lateinit var binding: FragmentSigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(layoutInflater)


        SetListner()

        binding.btnSignin.setOnClickListener(View.OnClickListener {
            if (isValidation()){
                Toast.makeText(requireActivity(), "validated", Toast.LENGTH_SHORT).show()
            }
          // setData()
        })
        binding.btnForget.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireActivity(), "Forget button clicked", Toast.LENGTH_SHORT).show()
        })

        return (binding.root)
    }

    private fun setData() {

        var email = binding.etEmail.text.toString().trim()
        var pass = binding.tvPassword.editText.toString().trim()

        //requestFocues()

        if (email.isEmpty()){
            //seteror(binding.tvEmail,"Enter Email")

            if (isValidation()){
                //Toast.makeText(requireActivity(), "validated", Toast.LENGTH_SHORT).show()
            }

        }else if(pass.isEmpty()){
          //  seteror(binding.tvPassword,"Enter password")

        }

    }

    /*private fun requestFocues() {
        binding.tvEmail.setBackgroundResource(R.color.white)
        binding.tvPassword.setBackgroundResource(R.color.white)

    }*/

    /*private fun seteror(editText: TextInputLayout?=null, error :String){

        editText?.let {
            it.setBackgroundResource(R.drawable.gragiyant)
            it.requestFocus()
        }
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()

    }*/


    fun SetListner(){
          binding.etEmail.addTextChangedListener(TextFieldValidation(binding.etEmail))
          binding.etPassword.addTextChangedListener(TextFieldValidation(binding.etPassword))
    }

    inner class TextFieldValidation(var view: View): TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
          when(view.id){
             R.id.et_Email->{
                 validateUserName()
             }
              R.id.et_password->{
                  ValidationPass()
              }

          }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }
     fun isValidation(): Boolean{
         validateUserName()&&ValidationPass()
         return true
     }


     fun validateUserName(): Boolean {
        if (binding.etEmail.text.toString().trim().isEmpty()) {
            binding.tvEmail.error = "Eamil Address"
            binding.etEmail.requestFocus()
            return false
        } else {
            binding.tvEmail.isErrorEnabled = false
        }

        return true
    }

    fun ValidationPass():Boolean{
        if(binding.etPassword.text.toString().trim().isEmpty()){
           binding.tvPassword.error="Password"
           binding.etPassword.requestFocus()
        }
        else{
           binding.tvPassword.isErrorEnabled=false
        }
        return  true
    }

}