package com.tutor93.menampilkanarray.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.base.League
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.invisible
import com.tutor93.menampilkanarray.main.detail.detailmatch.DetailMatchActivity
import com.tutor93.menampilkanarray.main.match.MatchAdapter
import com.tutor93.menampilkanarray.main.match.MatchPresenter
import com.tutor93.menampilkanarray.main.match.MatchView
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchActivity : AppCompatActivity(), MatchView, SearchView.OnQueryTextListener {
    private var eventList: MutableList<Event> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter

    override fun hideLoading() {
        listEvent.visible()
        progressBar.gone()
    }
    override fun showLoading() {
        listEvent.invisible()
        if (!swipeRefresh.isRefreshing) progressBar.visible()
    }

    override fun showMatchList(matchList: List<Event>) {
        swipeRefresh.isRefreshing = false
        eventList.clear()
        eventList.addAll(matchList.takeLast(15))
        adapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        p0?.let {
            presenter.searchEvent(p0)
        }
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    private lateinit var mSearchView: SearchView
    private var mQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        linearLayout {
            lparams(matchParent, matchParent)
            setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.white))
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeColors(
                    android.support.design.R.attr.colorAccent,
                    ContextCompat.getColor(ctx, android.R.color.holo_green_light),
                    ContextCompat.getColor(ctx, android.R.color.holo_orange_light),
                    ContextCompat.getColor(ctx, android.R.color.holo_red_light))

                relativeLayout {
                    lparams(matchParent, matchParent)

                    listEvent = recyclerView {
                        lparams(matchParent, matchParent)
                        layoutManager = LinearLayoutManager(ctx)
                        addItemDecoration(DividerItemDecoration(ctx, ClipDrawable.HORIZONTAL))
                    }
                    progressBar = progressBar { gone() }.lparams{centerHorizontally()}
                }
            }
        }

        adapter = MatchAdapter(eventList) {
            startActivityForResult<DetailMatchActivity>(102, "data" to it)
        }
        listEvent.adapter = adapter
        presenter = MatchPresenter(this, ApiRepository(), Gson())

        swipeRefresh.onRefresh {
            showLoading()
            presenter.getMatchList(League.id, true)
            swipeRefresh.isRefreshing = false
        }

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                mQuery = query
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        mSearchView = searchItem.actionView as SearchView
        setupSearchView()

        if (mQuery != null) {
            mSearchView.setQuery(mQuery, true)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return false
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
    }

    private fun setupSearchView() {
        mSearchView.setIconifiedByDefault(false)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchables = searchManager.searchablesInGlobalSearch
        var info = searchManager.getSearchableInfo(componentName)
        for (inf in searchables) {
            if (inf.suggestAuthority != null && inf.suggestAuthority.startsWith("applications")) {
                info = inf
            }
        }
        mSearchView .setSearchableInfo(info)
        mSearchView .setOnQueryTextListener(this)
        mSearchView .isFocusable = false
        mSearchView .isFocusableInTouchMode = false
        mSearchView .setIconifiedByDefault(false)
    }

}
