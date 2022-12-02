package app.statussaver.savestatus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter : FragmentPagerAdapter {

      var fraList:ArrayList<Fragment> = ArrayList()
      var fraTitleList:ArrayList<String> =ArrayList()


    constructor(supportFragment:FragmentManager)
            : super(supportFragment)

    override fun getCount(): Int {
        return fraList.size
    }

    override fun getItem(position: Int): Fragment {
        return fraList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //return super.getPageTitle(position)
        return  fraTitleList.get(position)
    }

    fun addFragment(fragment: Fragment,title:String){
       fraList.add(fragment)
       fraTitleList.add(title)
    }
}