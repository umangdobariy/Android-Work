package com.example.xmaltofragmnetdatapassdesignlec_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.xmaltofragmnetdatapassdesignlec_9.Fragments.Communicator
import com.example.xmaltofragmnetdatapassdesignlec_9.Fragments.SecondFragment

class MainActivity : AppCompatActivity(),Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun sendData(name: String, age: Int) {
        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_second)

        if (fragment is SecondFragment)
        {
            fragment.updatesendData(name,age)
        }

        Toast.makeText(applicationContext,"name:$name\n age:$age",Toast.LENGTH_SHORT).show()
    }
}