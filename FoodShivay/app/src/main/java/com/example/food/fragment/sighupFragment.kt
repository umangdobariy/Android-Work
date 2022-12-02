package com.example.food.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.food.Activity.ForgetActivity
import com.example.food.Activity.HomeActivity
import com.example.food.Prefrence.Prefrence
import com.example.food.R
import com.example.food.databinding.FragmentSighupBinding
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class sighupFragment : Fragment() {
    lateinit var binding: FragmentSighupBinding
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View? {
        // Inflate the layout for this fragment
        binding = FragmentSighupBinding.inflate(layoutInflater)

        //set data in method
        // setListner()
        setListner()
        //clicke event
        binding.btnSignup.setOnClickListener(View.OnClickListener {


            if (binding.etName.text.toString().isEmpty()) {
                binding.tbName.isErrorEnabled = false
                binding.tbName.error = "enter name"
                binding.etName.requestFocus()
            } else if (binding.etEmail.text.toString().isEmpty()) {
                binding.tbEmail.isErrorEnabled = false
                binding.tbEmail.error = "enter email"
                binding.etEmail.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()) {
                binding.tbEmail.isErrorEnabled = false
                binding.tbEmail.error = "not valid eamil"
                binding.etEmail.requestFocus()
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.tbPass.isErrorEnabled = false
                binding.tbPass.error = "enter password"
                binding.etPassword.requestFocus()
            } else if (!PASSWORD_PATTERN.matcher(binding.etPassword.text).matches()) {
                binding.tbPass.isErrorEnabled = false
                binding.tbPass.error = "not match password"
                binding.etPassword.requestFocus()
            } else if (binding.etCpassword.text.toString().isEmpty()) {
                binding.tbCpass.isErrorEnabled=false
                binding.tbCpass.error="enter CpassWord"
                binding.etCpassword.requestFocus()
            } else if (!binding.etCpassword.text.toString().equals(binding.etPassword.text.toString())) {
                binding.tbCpass.isErrorEnabled = false
                binding.tbCpass.error = "not match Password to CPassword"
                binding.etCpassword.requestFocus()
            } else {
               // Toast.makeText(requireContext(), "validtion", Toast.LENGTH_SHORT).show()

                var name=binding.etName.text.toString().trim()
                var Email=binding.etEmail.text.toString().trim()
                var Password=binding.etPassword.text.toString().trim()
                var CPassword=binding.etCpassword.text.toString().trim()


                var manager=Prefrence(requireContext())
                manager.SetLoginStatus(true)
                manager.SaveSignUp(sname =name, semail = Email, sPassword = Password, sCpassword = CPassword)
                Toast.makeText(requireActivity(), "account Created", Toast.LENGTH_SHORT).show()

                var intet=Intent(requireContext(),HomeActivity::class.java)
                startActivity(intet)

            }


            /*if(binding.etEmail.text.toString().isEmpty()){
                validationemail()
                Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()
            }
            else if(binding.etPassword.text.toString().isEmpty()){
                validationpass()
                Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()

            }
            else if(binding.etCpassword.text.toString().isEmpty()){
                comfirmpass()
                Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()
            }
            else{
                if(isValidation()){
                    var email = binding.etEmail.text.toString().trim()
                    var pass = binding.etPassword.text.toString().trim()
                    Signup(email, pass)
                }

            }*/

            //if (isValidation()) {
            // Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()
            // return true

            //}

            /* if(validationemail()){
                 Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()

             }
             else if(validationpass()){
                 Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()
             }*/
            //isValidation()
            // else {
            //   var email = binding.etEmail.text.toString().trim()
            //  var pass = binding.etPassword.text.toString().trim()
            //  Signup(email, pass)
            //}
            //setListner()

            /*  var email = binding.etEmail.text.toString().trim()
              var pass = binding.etPassword.text.toString().trim()

              if (!validationemail(email)) {
                  Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()
              } else if (validationpass(pass)) {
                  Toast.makeText(requireActivity(), "please validation", Toast.LENGTH_SHORT).show()
              } else {
                  Toast.makeText(requireActivity(), "form fully filled", Toast.LENGTH_SHORT).show()
                 // startActivity(Intent(requireActivity(), HomeActivity::class.java))
                  // Signup(email, pass)
              }

  */
        })



       // binding.btnSignup1.setOnClickListener {
            //var manager=Prefrence(requireContext())
            //manager.getData().

       // }

        binding.btnForget.setOnClickListener(View.OnClickListener {
            // Toast.makeText(requireActivity(), "Forgot Button Clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireActivity(), ForgetActivity::class.java))
        })

        return (binding.root)

    }

    private fun Signup(email: String, pass: String) {
        var manager = Prefrence(requireActivity())
        manager.SaveData(email, pass)
        manager.SetLoginStatus(true)
        Toast.makeText(requireActivity(), "Account Successfully Create", Toast.LENGTH_SHORT).show()
        //startActivity(Intent(requireActivity(), HomeActivity::class.java))

    }


    private fun setListner() {
        Toast.makeText(requireActivity(), "set listner", Toast.LENGTH_SHORT).show()
        binding.etName.addTextChangedListener(TextFieldValidation(binding.etName))
        binding.etEmail.addTextChangedListener(TextFieldValidation(binding.etEmail))
        binding.etPassword.addTextChangedListener(TextFieldValidation(binding.etPassword))
        binding.etCpassword.addTextChangedListener(TextFieldValidation(binding.etCpassword))

    }

    inner class TextFieldValidation(var view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // binding.etCpassword.text=null

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            when (view.id) {
                R.id.et_Name -> {
                    UserName()
                }
                R.id.et_Email -> {
                    validateUserName()
                    Toast.makeText(requireActivity(), "id valo code", Toast.LENGTH_SHORT).show()
                }
                R.id.et_password -> {
                    ValidationPass()
                    Toast.makeText(requireActivity(), "id valo code", Toast.LENGTH_SHORT).show()
                }
                R.id.et_Cpassword -> {
                    comfirmpass()
                    Toast.makeText(requireActivity(), "id valo code", Toast.LENGTH_SHORT).show()
                }

            }

        }


        override fun afterTextChanged(p0: Editable?) {
            // var conform=binding.etCpassword.text.toString()
            // binding.etCpassword.text=null
        }
    }

    fun UserName(): Boolean {
        if (binding.etName.text.toString().isEmpty()) {
            binding.etName.requestFocus()
        } else {
            binding.tbName.isErrorEnabled = false
        }
        return true
    }


    fun validateUserName(): Boolean {
        if (binding.etEmail.text.toString().trim().isEmpty()) {
            binding.tbEmail.error = "Email Address"
            binding.etEmail.requestFocus()
            return false
        } else {
            binding.tbEmail.isErrorEnabled = false
        }

        return true
    }

    fun ValidationPass(): Boolean {
        if (binding.etPassword.text.toString().trim().isEmpty()) {
            binding.tbPass.error = "Password"
            binding.etPassword.requestFocus()
        } else {
            binding.tbPass.isErrorEnabled = false
        }
        return true
    }


    private fun comfirmpass(): Boolean {
        if (binding.etCpassword.text.toString().trim().isEmpty()) {
            //binding.etCpassword.setText("")
            binding.tbCpass.error = " Enter Current Password"
            //binding.etCpassword.text=null
            binding.etCpassword.requestFocus()
            return false
        }
        /* else if (binding.etCpassword.text.toString() != binding.etPassword.text.toString()) {
            binding.etCpassword.setText("")
            return false
        }*/
        else {
            binding.tbCpass.isErrorEnabled = false
        }
        return true
    }


    /*private fun isValidation(): Boolean {
        if (validationemail() && validationpass() && comfirmpass()) {
            return true

        } else {
            var email = binding.etEmail.text.toString().trim()
            var pass = binding.etPassword.text.toString().trim()
            Signup(email, pass)

        }
        return false

    }


    private fun comfirmpass(): Boolean {


        if (binding.etCpassword.text.toString().trim() != binding.etPassword.text.toString()
                .trim()
        ) {
            //binding.etCpassword.setText("")
            binding.tbCpass.error = " Enter Current Password"
            //binding.etCpassword.text=null
            binding.etCpassword.requestFocus()

            return false
        } else if (binding.etCpassword.text.toString() != binding.etPassword.text.toString()) {
            binding.etCpassword.setText("")
            return false
        } else {
            binding.tbCpass.isErrorEnabled = false

        }

        return true
    }

    private fun validationemail(): Boolean {
        var emiil = binding.etEmail.text.toString()
        if (binding.etEmail.text.toString().trim().isEmpty()) {
            binding.tbEmail.error = "Email Addresss"
            binding.etEmail.requestFocus()
            return true
        } else {
            binding.tbEmail.isErrorEnabled = false
        }
        return Patterns.EMAIL_ADDRESS.matcher(emiil).matches()
    }

    private fun validationpass(): Boolean {
        var regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$"
        if (binding.etPassword.text.toString().trim().isEmpty()) {
            binding.tbPass.error = "Enter Password"
            binding.etPassword.requestFocus()
            return false
        } else {
            binding.tbPass.isErrorEnabled = false
        }
        return true// Pattern.compile(regex).matcher(password).matches()
    }*/


    private fun setData() {
/*

        //sarte in color seting

       // val email = binding.tvEmail.editText.toString().trim()
      //  val pass = binding.tvPassword.editText.toString().trim()
       // val cpass = binding.tvCpassword.editText.toString().trim()

        requestFocues()

        if (email.isEmpty()){
            seteror(binding.tvEmail,"Enter Emaile Address")

        }else if(pass.isEmpty()){
            seteror(binding.tvPassword ,"Enter Password")
        }else{
            seteror(binding.tvCpassword,"Enter Comfirm Password")
        }

*/

    }
    /* private fun seteror(editText: TextInputLayout, error:String){

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
 */


}


