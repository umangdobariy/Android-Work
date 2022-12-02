package app.statussaver.savestatus.tabFragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Environment
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.Visibility
import app.statussaver.R
import app.statussaver.databinding.FragmentImageBinding
import app.statussaver.savestatus.adapter.saveStatusadapter
import app.statussaver.savestatus.model.saveStatusModel
import com.google.android.material.tabs.TabLayout
import java.io.File


class ImageFragment : Fragment() {

    lateinit var binding:FragmentImageBinding
    lateinit var adapter: saveStatusadapter
    var listImg= mutableListOf<saveStatusModel>()
    var whatspp_status_folder_path="/WhatsApp/Media/.status"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentImageBinding.inflate(inflater,container,false)

        init()

        return binding.root
       // return inflater.inflate(R.layout.fragment_image, container, false)
    }

    fun init(){

         addData()

         adapter= saveStatusadapter(requireContext(),listImg)
         var manager=GridLayoutManager(requireContext(),3)
         binding.rvListWhatImg.layoutManager=manager
         binding.rvListWhatImg.adapter=adapter

    }

    fun addData(){


        val result=readDatashardPerf()


        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))
        listImg.add(saveStatusModel(R.drawable.image_logo))

    }

    private fun readDatashardPerf(): Boolean {

        val Shard=requireContext().getSharedPreferences("Data_path",MODE_PRIVATE)
        var uriPath=Shard.getString("Path")


        //var Uri_path= File(Environment.getExternalStorageDirectory().toString()+whatspp_status_folder_path)
        //var listFile=File
        //var file=File(Environment.getExternalStorageDirectory().toString()+whatspp_status_folder_path)
        //var listFile=

        return false
    }

    fun getimagePath(){

    }
}