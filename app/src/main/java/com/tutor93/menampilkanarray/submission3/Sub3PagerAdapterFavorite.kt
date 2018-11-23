package com.tutor93.menampilkanarray.submission3

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.tutor93.menampilkanarray.latihan4_footballclub.FavoriteFragment
import com.tutor93.menampilkanarray.latihan4_footballclub.FavoriteFragmentMatch
import com.tutor93.menampilkanarray.submission2.Event.EventNextFragment
import com.tutor93.menampilkanarray.submission2.Event.EventLastFragment

class Sub3PagerAdapterFavorite(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return FavoriteFragment()
            1 -> return FavoriteFragmentMatch()
            //2 -> return FavoriteFragment()
        }
        return null
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Teams"
            1 -> "Match"
            //2 -> "Favorite"
            else -> null
        }
    }
}