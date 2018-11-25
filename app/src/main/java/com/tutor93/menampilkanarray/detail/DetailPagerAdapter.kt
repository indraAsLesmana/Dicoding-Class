package com.tutor93.menampilkanarray.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.tutor93.menampilkanarray.base.BaseStateAdapter
import com.tutor93.menampilkanarray.detail.detailplayer.DetailPlayerListFrag
import com.tutor93.menampilkanarray.detail.detailteam.DetailTeamListFrag

class DetailPagerAdapter(fm: FragmentManager): BaseStateAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return DetailTeamListFrag()
            1 -> return DetailPlayerListFrag()
        }
        return null
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Team"
            1 -> "Player"
            else -> null
        }
    }
}