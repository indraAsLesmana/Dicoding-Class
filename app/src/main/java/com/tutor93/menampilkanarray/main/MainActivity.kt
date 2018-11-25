package com.tutor93.menampilkanarray.main

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.tutor93.menampilkanarray.*
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.detail.detailteam.DetailTeam
import com.tutor93.menampilkanarray.match.League
import com.tutor93.menampilkanarray.match.MatchLastFragment
import com.tutor93.menampilkanarray.match.MatchNextFragment
import com.tutor93.menampilkanarray.model.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.viewPager

class MainActivity: AppCompatActivity(), MainView, SearchView.OnQueryTextListener{
    private lateinit var presenter      : MainPresenter
    private lateinit var mTab           : TabLayout
    private lateinit var vPager         : ViewPager
    private lateinit var btmNav         : BottomNavigationView
    private lateinit var spinner        : Spinner
    private lateinit var spinnerMatch   : Spinner
    private lateinit var spinnerLayout  : LinearLayout
    private lateinit var swipeRefresh   : SwipeRefreshLayout
    private lateinit var progressBar    : ProgressBar
    private lateinit var listiTeam      : RecyclerView
    private lateinit var appBar         : AppBarLayout
    private lateinit var layTeams       : LinearLayout
    private lateinit var adapter        : MainTeamListAdapter
    private lateinit var mSearchView    : SearchView
    private lateinit var tabActive      : String

    private var mSelectedLiga: String        = League.name
    private var mAdapter                     = MainPagerAdapter(supportFragmentManager, this)
    private var mAdapterFavorite             = MainPagerAdapterFavorite(supportFragmentManager, this)
    private var mQuery: String?              = null
    private var teamsList: MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation = 0f
        supportActionBar?.title     = getString(R.string.label_footballmatch)
        presenter                   = MainPresenter(this, ApiRepository(), Gson())
        tabActive                   = getString(R.string.match)

        /**
         * initial layout
         * */
        relativeLayout {
            lparams(matchParent, matchParent)
            appBar = appBarLayout {
                id   = R.id.appBarLayout
                mTab = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    lparams(matchParent, wrapContent) {
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
                spinnerLayout = linearLayout {
                    id = R.id.spinnerLayout
                    setBackgroundColor(Color.WHITE)
                    spinnerMatch = spinner{}.lparams(matchParent, wrapContent)
                }.lparams(matchParent, wrapContent)

                vPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
            }.lparams{
                height  = matchParent
                width   = matchParent
                above(R.id.bottom_navigation)
            }

            layTeams = linearLayout {
                lparams(matchParent, matchParent)
                orientation = LinearLayout.VERTICAL
                topPadding  = dip(16)
                leftPadding = dip(16)
                rightPadding= dip(16)
                gone()

                /*spinner*/
                spinner = spinner()

                /*swiperefresh*/
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeColors(
                        android.support.design.R.attr.colorAccent,
                        ContextCompat.getColor(ctx, android.R.color.holo_green_light),
                        ContextCompat.getColor(ctx, android.R.color.holo_orange_light),
                        ContextCompat.getColor(ctx, android.R.color.holo_red_light))
                    relativeLayout {
                        lparams(matchParent, matchParent)
                        listiTeam = recyclerView {
                            lparams(matchParent, matchParent)
                            layoutManager = LinearLayoutManager(ctx)
                        }
                        progressBar = progressBar {}.lparams{centerHorizontally()}
                    }
                }
            }

            btmNav = bottomNavigationView {
                id = R.id.bottom_navigation
                backgroundColor = Color.WHITE
            }.lparams{
                width = matchParent
                alignParentBottom()
            }
        }
        btmNav.inflateMenu(R.menu.bottom_navigation_menu)
        vPager.adapter = mAdapter
        vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTab))
        mTab.setupWithViewPager(vPager)


        adapter = MainTeamListAdapter(teamsList) {
            when {
                !it.teamId.isNullOrEmpty()
                -> startActivity<DetailTeam>("data" to it.teamId!!)
                else -> showMessage("id null, try another data")
            }
        }
        listiTeam.adapter = adapter
        val spinerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.league))
        spinner.adapter   = spinerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getTeamList(spinner.selectedItem.toString())
            }
        }
        spinnerMatch.adapter = spinerAdapter
        spinnerMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (tabActive == getString(R.string.match)){
                    mSelectedLiga = spinnerMatch.selectedItem.toString()
                    refreshLastMatch()
                    refreshNextMatch()
                }
            }

        }
        swipeRefresh.onRefresh {
            showLoading()
            presenter.getTeamList(spinner.selectedItem.toString())
        }

        btmNav.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                resources.getIdentifier("teams", "id", packageName) -> {
                    showMatchView()
                    vPager.adapter = mAdapter
                    vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTab))
                    mTab.setupWithViewPager(vPager)
                    tabActive = getString(R.string.match)
                    true
                }
                resources.getIdentifier("teamsNext", "id", packageName) -> {
                    showTeamsList()
                    tabActive = getString(R.string.teams)
                    true
                }
                resources.getIdentifier("favorites", "id", packageName) -> {
                    showFavorite()
                    vPager.adapter = mAdapterFavorite
                    vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTab))
                    mTab.setupWithViewPager(vPager)
                    tabActive = getString(R.string.favorites)
                    true
                }
                else -> { true }
            }
        }
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teamsList   .clear()
        teamsList   .addAll(data)
        adapter     .notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater    = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem  = menu.findItem(R.id.action_search)
        mSearchView     = searchItem.actionView as SearchView
        setupSearchView()
        if (mQuery != null) {
            mSearchView.setQuery(mQuery, false)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when {
            item?.itemId == R.id.action_search && tabActive == getString(R.string.match)
            -> {
                startActivity<SearchActivity>()
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupSearchView() {
        mSearchView.setIconifiedByDefault(false)
        val searchManager   = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchables     = searchManager.searchablesInGlobalSearch
        var info            = searchManager.getSearchableInfo(componentName)
        for (inf in searchables) {
            if (inf.suggestAuthority != null
                && inf.suggestAuthority.startsWith("applications")
            ) {
                info = inf
            }
        }
        mSearchView .setSearchableInfo(info)
        mSearchView .setOnQueryTextListener(this)
        mSearchView .isFocusable = false
        mSearchView .isFocusableInTouchMode = false
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        presenter.searchTeam(p0)
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    private fun refreshLastMatch() {
        val frag = mAdapter.getRegisteredFragment(0)
        (frag as MatchLastFragment).changeLiga(mSelectedLiga)
    }

    private fun refreshNextMatch() {
        val frag = mAdapter.getRegisteredFragment(1)
        (frag as MatchNextFragment).changeLiga(mSelectedLiga)
    }

    override fun hideLoading() {
        listiTeam   .visible()
        progressBar .gone()
    }
    override fun showLoading() {
        listiTeam   .invisible()
        if (!swipeRefresh.isRefreshing) progressBar.visible()
    }

    private fun showFavorite () {
        layTeams     .gone()
        appBar       .visible()
        spinnerLayout.gone()
    }

    private fun showMatchView() {
        layTeams     .gone()
        appBar       .visible()
        spinnerLayout.visible()
    }

    private fun showTeamsList() {
        layTeams    .visible()
        appBar      .gone()
    }

}