package com.example.food.adaptar

import androidx.core.os.persistableBundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.food.databinding.FragmentSighupBinding
import com.example.food.databinding.FragmentSigninBinding
import com.example.food.fragment.sighupFragment
import com.example.food.fragment.signinFragment

class viewpager_adoptor(var fragment: FragmentManager,var tabcount:Int):FragmentPagerAdapter(fragment) {

   // lateinit var sighupFragment: sighupFragment
   //lateinit var signinFragment: signinFragment
    override fun getCount(): Int {
        return tabcount

    }

    override fun getItem(position: Int): Fragment {

        return when(position)
        {
            0->signinFragment()
            1->sighupFragment()
            else->signinFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if(position==0){
            "SignIn"
        }else{
            "SignUp"
        }
    }
}