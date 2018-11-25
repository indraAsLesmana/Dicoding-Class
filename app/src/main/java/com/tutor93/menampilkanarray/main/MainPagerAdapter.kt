package com.tutor93.menampilkanarray.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.base.BaseStateAdapter
import com.tutor93.menampilkanarray.match.MatchLastFragment
import com.tutor93.menampilkanarray.match.MatchNextFragment

class MainPagerAdapter(fm: FragmentManager, private val context: Context): BaseStateAdapter(fm) {
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
            0    -> context.getString(R.string.label_lastmatch)
            1    -> context.getString(R.string.label_next)
            else -> null
        }
    }
}