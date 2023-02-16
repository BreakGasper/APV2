package com.breakapp.apv2.ui.configs.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.breakapp.apv2.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerAdapter(context: Context,fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()

    private val tabTitle: ArrayList<String> = ArrayList()
    private val tabIcon: ArrayList<Int> = ArrayList()

    private  val context:Context=context

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment, s: String, icon : Int) {
        fragmentList.add(fragment)
        tabTitle.add(s)
        tabIcon.add(icon)


    }

/*    private fun saveTitleFragment(fg_position:Int){
        val sharedPreference =  context.getSharedPreferences("FRAG_TITLE",Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt("fg_position",fg_position)
        editor.commit()
    }*/

    fun getTitles(tabLayout: TabLayout, viewPager: ViewPager2) {
        /*  //        val tabTtiles = arrayOf("Home",  "Profile")
  //        TabLayoutMediator(
  //            tabLayout, viewPager
  //        ) { myTabLayout: TabLayout.Tab, position: Int ->
  //            myTabLayout.text = tabTitle.get(position)
  //        }.attach()
  //        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE)
  //        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.svg_pizza))
  */
        TabLayoutMediator(
            tabLayout, viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setIcon(tabIcon.get(position))
            tab.setText(tabTitle.get(position))

        }.attach()
//        saveTitleFragment(tabTitle.get(position))
    }

    override fun getItemCount(): Int {
        return fragmentList.size

    }


}