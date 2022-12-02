package com.example.custombottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.custombottomnavigation.fragments.*

class MainActivity : AppCompatActivity() {

    private val fragmentManger = supportFragmentManager
    private val homeFrag = HomeFragment()
    private val addFrag = AddFragment()
    private val searchFrag = SearchFragment()
    private val notifiyFrag = NotificationFragment()
    private val profileFrag = ProfileFragment()
    // call id
    private lateinit var btnH : ImageView
    private lateinit var btnA : ImageView
    private lateinit var btnS : ImageView
    private lateinit var btnN : ImageView
    private lateinit var btnP : ImageView

    private lateinit var manger : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set find id view
        btnH = findViewById(R.id.home)
        btnA = findViewById(R.id.add)
        btnN = findViewById(R.id.notification)
        btnP = findViewById(R.id.account)
        btnS = findViewById(R.id.serch)



        //set first fragments
        manger = fragmentManger.beginTransaction()
            .replace(R.id.myView,homeFrag)
            manger.commit()
        btnH.setImageResource(R.drawable.ic_baseline_home_24)
    }

    fun addFragOnClick(view: View) {

        manger = fragmentManger.beginTransaction()
        when(view){
            btnH ->{
                manger.replace(R.id.myView,homeFrag)
                    .commit()
                btnH.setImageResource(R.drawable.ic_baseline_home_24)
                btnN.setImageResource(R.drawable.ic_baseline_notifications_24)
                btnA.setImageResource(R.drawable.ic_baseline_add_24)
                btnS.setImageResource(R.drawable.ic_baseline_search_24)
                btnP.setImageResource(R.drawable.ic_baseline_account_circle_24)
            }
            btnA ->{
                manger.replace(R.id.myView,addFrag)
                    .commit()
                btnH.setImageResource(R.drawable.ic_baseline_home_24)
                btnN.setImageResource(R.drawable.ic_baseline_notifications_24)
                btnA.setImageResource(R.drawable.ic_baseline_add_24)
                btnS.setImageResource(R.drawable.ic_baseline_search_24)
                btnP.setImageResource(R.drawable.ic_baseline_account_circle_24)
            }
            btnS ->{
                manger.replace(R.id.myView,searchFrag)
                    .commit()
                btnH.setImageResource(R.drawable.ic_baseline_home_24)
                btnN.setImageResource(R.drawable.ic_baseline_notifications_24)
                btnA.setImageResource(R.drawable.ic_baseline_add_24)
                btnS.setImageResource(R.drawable.ic_baseline_search_24)
                btnP.setImageResource(R.drawable.ic_baseline_account_circle_24)
            }
            btnN ->{
                manger.replace(R.id.myView,notifiyFrag)
                    .commit()
                btnH.setImageResource(R.drawable.ic_baseline_home_24)
                btnN.setImageResource(R.drawable.ic_baseline_notifications_24)
                btnA.setImageResource(R.drawable.ic_baseline_add_24)
                btnS.setImageResource(R.drawable.ic_baseline_search_24)
                btnP.setImageResource(R.drawable.ic_baseline_account_circle_24)
            }
            btnP ->{
                manger.replace(R.id.myView,profileFrag)
                    .commit()
                btnH.setImageResource(R.drawable.ic_baseline_home_24)
                btnN.setImageResource(R.drawable.ic_baseline_notifications_24)
                btnA.setImageResource(R.drawable.ic_baseline_add_24)
                btnS.setImageResource(R.drawable.ic_baseline_search_24)
                btnP.setImageResource(R.drawable.ic_baseline_account_circle_24)
            }
        }

    }
}