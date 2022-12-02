package app.statussaver.savestatus.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import app.statussaver.R
import app.statussaver.savestatus.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class MessageRecovery : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view= inflater.inflate(R.layout.fragment_message_recovery, container, false)


//        var tab_toolbar=view.findViewById<Toolbar>(R.id.toolbar)
//        var tab_Layout=view.findViewById<TabLayout>(R.id.tablyout)
//        var tab_viewPager=view.findViewById<ViewPager>(R.id.viewData)
//
//        //requireAct(tab_toolbar)
//        tab_toolbar.title="Saved Status"
//        requireActivity().title=tab_toolbar.title
//        setupViewPager(tab_viewPager)
//        tab_Layout.setupWithViewPager(tab_viewPager)

        return view
    }

    private fun setupViewPager(tabViewpager: ViewPager) {
        var adapter= ViewPagerAdapter(requireActivity().supportFragmentManager)

        adapter.addFragment(SaveStatusFragment(),"Messages")
        adapter.addFragment(MessageRecovery(),"Media")

        tabViewpager.adapter=adapter
    }

}