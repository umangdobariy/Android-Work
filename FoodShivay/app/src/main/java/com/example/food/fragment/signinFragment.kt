package com.example.food.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.food.Activity.ForgetActivity
import com.example.food.Activity.HomeActivity
import com.example.food.Prefrence.Prefrence
import com.example.food.Prefrence.Prefrence.Companion.Email
import com.example.food.Prefrence.Prefrence.Companion.Name
import com.example.food.Prefrence.Prefrence.Companion.Pass
import com.example.food.Prefrence.Prefrence.Companion.Pref_Name
import com.example.food.R
import com.example.food.databinding.FragmentSigninBinding
import java.util.regex.Pattern


class signinFragment : Fragment() {
    lateinit var binding: FragmentSigninBinding


    //pattern==>  Abcd@2022
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(layoutInflater)


        SetListner()

        binding.btnSignin.setOnClickListener(View.OnClickListener {
            /*if(isValidation()){
                Toast.makeText(requireActivity(), "validated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireActivity(),HomeActivity::class.java))
                //data confirm
                //dataComgfirm()

            }*/

           //  var sherprefrence = requireActivity().getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
             //var email=binding.etEmail.text.toString().trim()
             //var Password=binding.etPassword.text.toString().trim()
           // var emailData:String=""
            //var PassData:String=""

           // sherprefrence.getString(Name,emailData)
           // sherprefrence.getString(Pass,PassData)

            var manager=Prefrence(requireContext())
            manager.SetLoginStatus(true)

            var prefEmail=manager.getPrefData(Email)
            var prefPass=manager.getPrefData(Pass)


            if (binding.etEmail.text.toString().isEmpty()) {
                binding.tvEmail.isErrorEnabled = false
                binding.tvEmail.error = "enter email"
                binding.etEmail.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()) {
                binding.tvEmail.isErrorEnabled = false
                binding.tvEmail.error = "not valid eamil"
                binding.etEmail.requestFocus()
            }
            /*else if(!binding.etEmail.text.toString().equals(emailData)){
                binding.tvEmail.isErrorEnabled = false
                binding.tvEmail.error = "please enter valid eamil"
                binding.etEmail.requestFocus()
            }*/

            // var email=binding.etEmail.text.toString().trim()

            else if(!prefEmail.equals(binding.etEmail.text.toString().trim())){
                binding.tvEmail.isErrorEnabled = false
                binding.tvEmail.error = "plase valid Enter Eamil"
                binding.etEmail.requestFocus()
            }
            else if (binding.etPassword.text.toString().isEmpty()) {
                binding.tvPassword.isErrorEnabled = false
                binding.tvPassword.error = "enter password"
                binding.etPassword.requestFocus()
            } else if (!PASSWORD_PATTERN.matcher(binding.etPassword.text).matches()) {
                binding.tvPassword.isErrorEnabled = false
                binding.tvPassword.error = "not match password"
                binding.etPassword.requestFocus()
            }
            else if(!prefPass.equals(binding.etPassword.text.toString().trim())){
                binding.tvPassword.isErrorEnabled = false
                binding.tvPassword.error = "please Enter valid password"
                binding.etPassword.requestFocus()
            }
            else {
                //Toast.makeText(requireContext(), "valid email", Toast.LENGTH_SHORT).show()
                //var email=binding.etEmail.text.toString().trim()
                //var Password=binding.etPassword.text.toString().trim()

                //var manager=Prefrence(requireContext())
                //var prefArra=manager.getData(email1 = email, password = Password)

                 //var sherprefrence = requireActivity().getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
                 //var edits = sherprefrence.edit()

                  //var emailData=sherprefrence.getString(Name,"")
                  //var PassData=sherprefrence.getString(Pass,"")

                  /*if(email.equals(emailData)){
                      Toast.makeText(requireContext(), "valid", Toast.LENGTH_SHORT).show()
                  }
                  else if(!email.equals(emailData)){
                      Toast.makeText(requireContext(), " not valid", Toast.LENGTH_SHORT).show()
                  }
                  else if(Password.equals(PassData)){
                      Toast.makeText(requireContext(), " valid password", Toast.LENGTH_SHORT).show()
                  }
                  else if(!Password.equals(PassData)){
                      Toast.makeText(requireContext(), "not valid password", Toast.LENGTH_SHORT).show()
                  }*/

                  var intet=Intent(requireContext(),HomeActivity::class.java)
                  startActivity(intet)

            }
        })
        binding.btnForget.setOnClickListener(View.OnClickListener {
            //Toast.makeText(requireActivity(), "Forget button clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireActivity(), ForgetActivity::class.java))

        })

        return (binding.root)
    }

    /* private fun dataComgfirm() {
         var manger = Prefrence(requireActivity())

         var email = binding.etEmail.text.toString().trim()
         var pass = binding.etPassword.text.toString().trim()

        var array = manger.GetData()
         array.let{

         }



     }*/

/*    private fun setData() {

        var email = binding.etEmail.text.toString().trim()
        var pass = binding.etPassword.text.toString().trim()

        //requestFocues()

        if (email.isEmpty()){
            //seteror(binding.tvEmail,"Enter Email")
            SetListner()

            if (isValidation()){
                //Toast.makeText(requireActivity(), "validated", Toast.LENGTH_SHORT).show()
            }

        }else if(pass.isEmpty()){
          //  seteror(binding.tvPassword,"Enter password")
            SetListner()

        }

    }*/

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


    fun SetListner() {
        binding.etEmail.addTextChangedListener(TextFieldValidation(binding.etEmail))
        binding.etPassword.addTextChangedListener(TextFieldValidation(binding.etPassword))
    }

    inner class TextFieldValidation(var view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            when (view.id) {
                R.id.et_Email -> {
                    validateUserName()
                }
                R.id.et_password -> {
                    ValidationPass()
                }

            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    fun isValidation(): Boolean {
        validateUserName() && ValidationPass()
        return true
    }


    fun validateUserName(): Boolean {
        if (binding.etEmail.text.toString().trim().isEmpty()) {
            binding.tvEmail.error = "Email Address"
            binding.etEmail.requestFocus()
            return false
        } else {
            binding.tvEmail.isErrorEnabled = false
        }

        return true
    }

    fun ValidationPass(): Boolean {
        if (binding.etPassword.text.toString().trim().isEmpty()) {
            binding.tvPassword.error = "Password"
            binding.etPassword.requestFocus()
        } else {
            binding.tvPassword.isErrorEnabled = false
        }
        return true
    }

}