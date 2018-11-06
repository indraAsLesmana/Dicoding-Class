package com.tutor93.menampilkanarray.submission3

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.google.gson.Gson
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.jsonString
import com.tutor93.menampilkanarray.model.League
import com.tutor93.menampilkanarray.model.response.LeagueResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager

class Sub3Activity: AppCompatActivity(), Sub3View{
    private var leagueList: MutableList<League> = mutableListOf()
    private lateinit var presenter: Sub3Presenter
    private lateinit var mTab: TabLayout
    private lateinit var vPager: ViewPager

    private lateinit var btmNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation = 0f
        supportActionBar?.title =
                String.format(
                    "%s (%s)", getString(R.string.label_footballmatch),
                    com.tutor93.menampilkanarray.submission2.Event.League.name
                )
        presenter = Sub3Presenter(this, ApiRepository(), Gson())

        // 1. load local Json add to list field just 10 data
        val alllistLeague = Gson().fromJson(
            jsonString("list_league.json"),
            LeagueResponse::class.java
        ).leagues as MutableList<League>
        leagueList = alllistLeague.asSequence().take(10).toMutableList()

        relativeLayout {
            lparams(matchParent, matchParent)
            appBarLayout {
                id = R.id.appBarLayout
                //lparams(matchParent, wrapContent)
                mTab = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    lparams(matchParent, wrapContent) {
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
                vPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
            }.lparams{
                height = matchParent
                width = matchParent
                above(R.id.bottom_navigation)
            }
            /*view {
                id = R.id.viewShadow
            }.lparams(matchParent, dip(4))*/
            btmNav = bottomNavigationView {
                id = R.id.bottom_navigation
                backgroundColor = Color.WHITE
            }.lparams{
                width = matchParent
                alignParentBottom()
            }
        }
        btmNav.inflateMenu(R.menu.bottom_navigation_menu)
        vPager.adapter = SubPagerAdapter(supportFragmentManager)
        vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTab))
        mTab.setupWithViewPager(vPager)

        /**
         * Syarat
         * "Semua fitur pada aplikasi sebelumnya harus tetap dipertahankan".
         *
         * ini jadi ada 2 navigasi :)
         * */
        btmNav.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                resources.getIdentifier("teams", "id", packageName) -> {
                    mTab.getTabAt(0)?.select()
                    true
                }
                resources.getIdentifier("teamsNext", "id", packageName) -> {
                    mTab.getTabAt(1)?.select()
                    true
                }
                resources.getIdentifier("favorites", "id", packageName) -> {
                    mTab.getTabAt(2)?.select()
                    true
                }
                else -> { true }
            }
        }
        mTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.position){
                    0->{ btmNav.selectedItemId = R.id.teams }
                    1->{ btmNav.selectedItemId = R.id.teamsNext }
                    2->{ btmNav.selectedItemId = R.id.favorites }
                }
            }
        })
    }

    // 2. make leagueList as optiomMenu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        leagueList.forEach {
            menu.add(0, it.idLeague?.toInt() ?: 0, 0, it.strLeague)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        supportActionBar?.title = String.format("%s (%s)", getString(R.string.label_footballmatch), item?.title)
        return false
    }
}