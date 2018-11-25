package com.tutor93.menampilkanarray.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.tutor93.menampilkanarray.*
import com.tutor93.menampilkanarray.ankoview.Ui
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.detail.detailmatch.DetailMatchActivity
import com.tutor93.menampilkanarray.model.Event
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivityForResult

class MatchLastFragment: Fragment(), MatchView {
    private lateinit var swipeRefresh   : SwipeRefreshLayout
    private lateinit var listEvent      : RecyclerView
    private lateinit var progressBar    : ProgressBar
    private lateinit var adapter        : MatchAdapter
    private lateinit var presenter      : MatchPresenter

    private var eventList       : MutableList<Event> = mutableListOf()
    private var leagueId        : String = League.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view     = Ui().createView(AnkoContext.create(ctx, container!!, false))
        swipeRefresh = view.find(R.id.matchViewSwiperefresh)
        listEvent    = view.find(R.id.matchViewRecyclerview)
        progressBar  = view.find(R.id.matchViewProgress)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MatchAdapter(eventList) {
            startActivityForResult<DetailMatchActivity>(102, "data" to it)
        }
        listEvent.adapter = adapter
        presenter         = MatchPresenter(this, ApiRepository(), Gson())

        swipeRefresh.onRefresh {
            showLoading()
            presenter.getMatchList(leagueId, true)
            swipeRefresh.isRefreshing = false
        }

        if (eventList.isEmpty()) presenter.getMatchList(leagueId, true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        League.id = item?.itemId.toString()
        presenter.getMatchList(League.id, true)
        return false
    }

    override fun hideLoading() {
        listEvent   .visible()
        progressBar .gone()
    }
    override fun showLoading() {
        listEvent   .invisible()
        if (!swipeRefresh.isRefreshing) progressBar.visible()
    }

    override fun showMatchList(matchList: List<Event>) {
        swipeRefresh   .isRefreshing = false
        eventList      .clear()
        eventList      .addAll(matchList)
        adapter        .notifyDataSetChanged()
    }

    fun changeLiga(mSelectedLiga: String) {
        leagueId = mSelectedLiga.withValidLigaId()
        presenter.getMatchList(leagueId, true)
    }
}