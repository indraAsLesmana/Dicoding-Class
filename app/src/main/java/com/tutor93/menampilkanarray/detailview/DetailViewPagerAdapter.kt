package com.tutor93.menampilkanarray.detailview

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class DetailViewPagerAdapter(fm: FragmentManager): SmartFragmentStatePagerAdapter(fm) {
    //This will contain your Fragment references:
    //This will contain your Fragment references:
    var fragments = arrayOfNulls<Fragment>(count)

    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return DetailViewFrag1()
            1 -> return DetailViewFrag2()
            //2 -> return FavoriteFragment()
        }
        return null
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Team"
            1 -> "Player"
            //2 -> "Favorite"
            else -> null
        }
    }
}