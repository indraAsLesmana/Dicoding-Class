package com.tutor93.menampilkanarray.submission3

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import com.tutor93.menampilkanarray.detailview.SmartFragmentStatePagerAdapter
import com.tutor93.menampilkanarray.latihan4_footballclub.FavoriteFragment
import com.tutor93.menampilkanarray.submission2.Event.EventNextFragment
import com.tutor93.menampilkanarray.submission2.Event.EventLastFragment

class Sub3PagerAdapter(fm: FragmentManager): SmartFragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return EventLastFragment()
            1 -> return EventNextFragment()
            //2 -> return FavoriteFragment()
        }
        return null
    }

    /*// Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<Fragment>()

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    // Unregister when the item is inactive
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    // Returns the fragment for the position (if instantiated)
    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }*/

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            1 -> "Next Match"
            //2 -> "Favorite"
            else -> null
        }
    }
}