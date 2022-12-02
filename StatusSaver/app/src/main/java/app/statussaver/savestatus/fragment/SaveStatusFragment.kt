package app.statussaver.savestatus.fragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import app.statussaver.R
import app.statussaver.databinding.FragmentImageBinding
import app.statussaver.databinding.FragmentSaveStatusBinding
import app.statussaver.savestatus.adapter.ViewPagerAdapter
import app.statussaver.savestatus.tabFragment.ImageFragment
import app.statussaver.savestatus.tabFragment.SavedFragment
import app.statussaver.savestatus.tabFragment.VideosFragment
import com.google.android.material.tabs.TabLayout


class SaveStatusFragment : Fragment() {

    lateinit var binding: FragmentSaveStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        var view= inflater.inflate(R.layout.fragment_save_status, container, false)
//
//        //var tab_toolbar=view.findViewById<Toolbar>(R.id.toolbar)
//        var tab_Layout=view.findViewById<TabLayout>(R.id.tablyout)
//        var tab_viewPager=view.findViewById<ViewPager>(R.id.viewData)
//
//        //tab_toolbar.title="Saved Status"
//        //requireActivity().title=tab_toolbar.title
//        setupViewPager(tab_viewPager)
//        tab_Layout.setupWithViewPager(tab_viewPager)
//
//        SetData()
//
//        return view


        binding=FragmentSaveStatusBinding.inflate(inflater,container,false)

        //var view= inflate(R.layout.fragment_save_status, false)

        //var tab_toolbar=view.findViewById<Toolbar>(R.id.toolbar)
        //var tab_Layout=view.findViewById<TabLayout>(R.id.tablyout)
        //var tab_viewPager=view.findViewById<ViewPager>(R.id.viewData)

        //tab_toolbar.title="Saved Status"
        //requireActivity().title=tab_toolbar.title

        setupViewPager(binding.viewData)
        binding.tablyout.setupWithViewPager(binding.viewData)

        SetData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

    }

    private fun setupViewPager(tabViewpager: ViewPager) {
         var adapter= ViewPagerAdapter(requireActivity().supportFragmentManager)

        adapter.addFragment(ImageFragment(),"Images")
        adapter.addFragment(VideosFragment(),"Videos")
        adapter.addFragment(SavedFragment(),"Saved")

        tabViewpager.adapter=adapter
    }

    private fun SetData() {

    }

}