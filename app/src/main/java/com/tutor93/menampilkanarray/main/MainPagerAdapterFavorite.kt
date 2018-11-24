package com.tutor93.menampilkanarray.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.latihan4_footballclub.FavoriteFragment
import com.tutor93.menampilkanarray.latihan4_footballclub.FavoriteFragmentMatch

class MainPagerAdapterFavorite(fm: FragmentManager, private val context: Context): FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return FavoriteFragment()
            1 -> return FavoriteFragmentMatch()
        }
        return null
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