package com.tutor93.menampilkanarray.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.main.favorite.FavoriteFragment

class MainPagerAdapterFavorite(fm: FragmentManager, private val context: Context): FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return FavoriteFragment()
            1 -> return FavoriteFragment()
        }
        return null
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        if (position == 1) (fragment as FavoriteFragment).isMatch = true
        return fragment
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.teams)
            1 -> context.getString(R.string.match)
            else -> null
        }
    }
}