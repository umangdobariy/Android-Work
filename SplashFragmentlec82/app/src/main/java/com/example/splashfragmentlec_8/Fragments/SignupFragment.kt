package com.example.splashfragmentlec_8.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.splashfragmentlec_8.R
import com.example.splashfragmentlec_8.Utils
import com.example.splashfragmentlec_8.databinding.FragmentSignupBinding
import java.util.regex.Pattern


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rdRegister.setOnClickListener {
            var name = binding.rdName.text.toString().trim()
            var email = binding.rdEmail.text.toString().trim()
            var contact = binding.rdContact.text.toString().trim()
            var password = binding.rdPassword.text.toString().trim()
            var cpassword = binding.rdCpassword.text.toString().trim()
//
//            if (isvalidEmail(email) && isvalidcontact(contact) && isvalidpassword(password) && isvalidcpassword(cpassword))
//            {
//                val intent= Intent(this,HomeActivity::class.java)
//                intent.putExtra("name",binding.rdName.text.toString())
//                intent.putExtra("email",binding.rdEmail.text.toString())
//                intent.putExtra("contact",binding.rdContact.text.toString())
//                intent.putExtra("password",binding.rdPassword.text.toString())
//                intent.putExtra("cpassword",binding.rdCpassword.text.toString())
//                startActivity(intent)
//            }else
//            {
//                Toast.makeText(this,"error in name or contact or email or password or cpassword",
//                    Toast.LENGTH_LONG).show()

//            if(name.isEmpty()  && contact.isEmpty() && email.isEmpty() && password.isEmpty() && cpassword.isEmpty()){
//            Toast.makeText(requireContext(),"error in name or contact or email or password or cpassword",
//                Toast.LENGTH_LONG).show()
//             }
            if (name.isEmpty())
            {
                binding.rdName.error = "Enter your name"
                Toast.makeText(requireContext(),"error name",
                    Toast.LENGTH_LONG).show()

            }
            else if (!isvalidcontact(contact))
            {
                binding.rdContact.error = "Enter your contact"
                Toast.makeText(requireContext(),"error contact",
                    Toast.LENGTH_LONG).show()

            }
            else if (!isvalidEmail(email))
            {
                binding.rdEmail.error = "Enter your email"

                Toast.makeText(requireContext(),"error email",
                    Toast.LENGTH_LONG).show()
            }
            else if (!isvalidpassword(password))
            {
                binding.rdPassword.error = "Enter your password"

                Toast.makeText(requireContext(),"error password",
                    Toast.LENGTH_LONG).show()

            }else if (!isvalidcpassword(cpassword))
            {
                binding.rdCpassword.error = "Enter your cpassword"
                Toast.makeText(requireContext(),"error cpassword",
                    Toast.LENGTH_LONG).show()

//                Utils().customToast("error cpassword")
            }

            else
            {
                Toast.makeText(requireContext(),"All fields are validated", Toast.LENGTH_SHORT).show()
            }
    }

    }
    private fun isvalidcontact(contact:String):Boolean
    {
        return contact.length==10
    }
    private fun isvalidEmail(email:String):Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isvalidpassword(password:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(password).matches()
    }
    private fun isvalidcpassword(cpassword:String):Boolean
    {
        return Pattern.compile(REGEX).matcher(cpassword).matches()
    }

}