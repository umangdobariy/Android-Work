package com.example.whatsapp

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
      return  when(position){
          0 -> { ImageFragment () }
          1 -> { StatusFragment ()}
          2 -> { SavedFragment ()}
          else -> {throw Resources.NotFoundException("Position Not Found ")}
      }
    }
}