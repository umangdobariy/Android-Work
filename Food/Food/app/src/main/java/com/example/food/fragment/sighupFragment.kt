package com.example.food.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.food.R
import com.example.food.databinding.FragmentSighupBinding
import com.google.android.material.textfield.TextInputLayout


class sighupFragment : Fragment() {
    lateinit var binding: FragmentSighupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?)
    : View? {
        // Inflate the layout for this fragment
        binding = FragmentSighupBinding.inflate(layoutInflater)


        //set data in method
        setListner()

        //clicke event
        binding.btnSignin.setOnClickListener(View.OnClickListener(){

            Toast.makeText(requireActivity(), "signup", Toast.LENGTH_SHORT).show()

        //setData()

        })
        binding.btnForget.setOnClickListener (View.OnClickListener {

            Toast.makeText(requireActivity(), "Forgot Button Clicked", Toast.LENGTH_SHORT).show()

        })
        return (binding.root)

    }

    private fun setListner() {
        binding.tvEmail

    }

    private fun setData() {

        //sarte in color seting

        val email = binding.tvEmail.editText.toString().trim()
        val pass = binding.tvPassword.editText.toString().trim()
        val cpass = binding.tvCpassword.editText.toString().trim()

        requestFocues()

        if (email.isEmpty()){
            seteror(binding.tvEmail,"Enter Emaile Address")

        }else if(pass.isEmpty()){
            seteror(binding.tvPassword ,"Enter Password")
        }else{
            seteror(binding.tvCpassword,"Enter Comfirm Password")
        }


    }
    private fun seteror(editText: TextInputLayout, error:String){

        editText?.let {
            it.setBackgroundResource(R.drawable.gragiyant)
            it.requestFocus()
        }
        Toast.makeText(requireActivity(), error , Toast.LENGTH_SHORT).show()

    }
    private fun requestFocues(){

        binding.tvEmail.setBackgroundResource(R.color.black)
        binding.tvPassword.setBackgroundResource(R.color.black)
        binding.tvCpassword.setBackgroundResource(R.color.black)



    }


}