package com.example.joblink.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var mFm = fm
    var mFragmentItem: ArrayList<Fragment> = ArrayList()
    var mFragmentTitle: ArrayList<String> = ArrayList()

    fun addFragment(fragmentItem: Fragment, fragmentTitle: String) {
        mFragmentItem.add(fragmentItem)
        mFragmentTitle.add(fragmentTitle)
    }

    override fun getItem(p0: Int): Fragment {
        return mFragmentItem[p0]
    }

    override fun getCount(): Int {
        return mFragmentItem.size
    }


}