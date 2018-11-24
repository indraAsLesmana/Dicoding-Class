package com.tutor93.menampilkanarray.submission2

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tutor93.menampilkanarray.match.MatchNextFragment
import com.tutor93.menampilkanarray.match.MatchLastFragment

class SubPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return MatchLastFragment()
            1 -> return MatchNextFragment()
        }
        return null
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            1 -> "Next Match"
            else -> null
        }
    }
}