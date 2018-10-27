package com.tutor93.menampilkanarray.submission2

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.Gson
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.jsonString
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.model.League
import com.tutor93.menampilkanarray.model.response.LeagueResponse
import com.tutor93.menampilkanarray.submission2.Event.BlankFragment
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class SubTwoActivity: AppCompatActivity(), SubTwoView, BlankFragment.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
    }

    private var leagueList: MutableList<League> = mutableListOf()
    private lateinit var presenter: SubTwoPresenter
    private lateinit var mTab: TabLayout
    private lateinit var vPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_footballmatch)
        supportActionBar?.elevation = 0f
        presenter = SubTwoPresenter(this, ApiRepository(), Gson())

        // 1. load local Json add to list field just 10 data
        val alllistLeague = Gson().fromJson(
            jsonString("list_league.json"),
            LeagueResponse::class.java
        ).leagues as MutableList<League>
        leagueList = alllistLeague.asSequence().take(10).toMutableList()

        verticalLayout {
            lparams(matchParent, matchParent)
            orientation = LinearLayout.VERTICAL
            appBarLayout {
                lparams(matchParent, wrapContent)
                mTab = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    lparams(matchParent, wrapContent) {
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
                vPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
            }
        }
        vPager.adapter = SubPagerAdapter(supportFragmentManager)
        vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTab))
        mTab.setupWithViewPager(vPager)
    }

    // 2. make leagueList as optiomMenu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        leagueList.forEach {
            menu.add(0, it.idLeague?.toInt()?:0, 0, it.strLeague)
        }
        return true
    }

    // 3. hit leagueList here
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        presenter.getMatchList(item?.itemId.toString())
        return true
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showMatchList(matchList: List<Event>?) {
        val match = matchList
    }

}