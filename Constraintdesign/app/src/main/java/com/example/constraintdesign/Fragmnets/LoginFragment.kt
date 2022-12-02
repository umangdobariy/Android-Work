package com.example.constraintdesign.Fragmnets

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.constraintdesign.R
import com.example.constraintdesign.databinding.FragmentLoginBinding
import java.util.regex.Pattern


class LoginFragment : Fragment() {

    private val REGEX = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button.setOnClickListener {

            var fragment = SignupFragment()
            var manager = requireActivity().supportFragmentManager
            var transaction = manager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        binding.button3.setOnClickListener {


            var email = binding.editTextTextEmailAddress8.text.toString().trim()
            var password = binding.editTextTextPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.editTextTextEmailAddress8.error = "Enter your email"
                Toast.makeText(requireActivity(), "error email", Toast.LENGTH_SHORT).show()
            } else if (!isvalidpassword(password))
            {
                binding.editTextTextPassword.error = "Enter your password"
                Toast.makeText(requireActivity(),"error password",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireActivity(),"all fields are validated",Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(),HomeFragment::class.java)
                intent.putExtra("EMAIL",email)
                intent.putExtra("PASSWORD",password)
                startActivity(intent)


            }
        }
    }

    private fun isvalidpassword(password: String): Boolean
    {
        return  Pattern.compile(REGEX).matcher(password).matches()
    }

}