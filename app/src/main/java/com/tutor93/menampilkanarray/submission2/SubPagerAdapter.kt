package com.tutor93.menampilkanarray.submission2

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tutor93.menampilkanarray.submission2.Event.NextEventFragment
import com.tutor93.menampilkanarray.submission2.Event.PastEventFragment

class SubPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when(p0){
            0-> return NextEventFragment()
            1-> return PastEventFragment()
        }
        return null
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Next Match"
            1 -> "Past Match"
            else -> null
        }
    }
}