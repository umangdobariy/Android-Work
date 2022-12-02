package com.example.navaigationlec_50

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navaigationlec_50.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            var name = it.getString("name")
            var email = it.getString("email")
            var id = it.getInt("id")

            binding.tvResult.text = """
                    name : $name
                    email : $email
                    id : $id
                """.trimIndent()
        }


        binding.btnNavigateHome.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

}