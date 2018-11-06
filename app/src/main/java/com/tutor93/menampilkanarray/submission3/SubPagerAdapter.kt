package com.tutor93.menampilkanarray.submission3

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tutor93.menampilkanarray.latihan4_footballclub.FavoriteFragment
import com.tutor93.menampilkanarray.submission2.Event.EventNextFragment
import com.tutor93.menampilkanarray.submission2.Event.EventLastFragment

class SubPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return EventLastFragment()
            1 -> return EventNextFragment()
            2 -> return FavoriteFragment()
        }
        return null
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            1 -> "Next Match"
            2 -> "Favorite"
            else -> null
        }
    }
}