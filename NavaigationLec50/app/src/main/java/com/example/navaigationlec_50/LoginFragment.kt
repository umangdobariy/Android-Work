package com.example.navaigationlec_50

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navaigationlec_50.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView2)

        binding.btnNavigateHome.setOnClickListener {

            navController.navigate(R.id.action_registerFragment_to_homeFragment)

        }

        binding.btnNavigateResgister.setOnClickListener {

            var bundle = Bundle()
            bundle.putString("name","tops tech")
            bundle.putString("email","tops@gmail.com")
            bundle.putInt("id",111)


            navController.navigate(R.id.action_registerFragment_to_homeFragment,bundle)

        }
    }


}