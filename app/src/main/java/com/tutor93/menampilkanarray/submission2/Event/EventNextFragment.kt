package com.tutor93.menampilkanarray.submission2.Event

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.invisible
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import com.tutor93.menampilkanarray.detailview.DetailEventActivity
import org.jetbrains.anko.support.v4.startActivity

class EventNextFragment: Fragment(), EventView {
    private var eventList: MutableList<Event> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: EventAdapter
    private lateinit var presenter: EventPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return Ui().createView(AnkoContext.create(ctx, container!!, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EventAdapter(eventList){
            it.isNextMatch = true
            startActivity<DetailEventActivity>("data" to it)
        }
        listEvent.adapter = adapter
        presenter = EventPresenter(this, ApiRepository(), Gson())

        swipeRefresh.onRefresh {
            League.id?.let {
                showLoading()
                presenter.getMatchList(it)
                return@let
            }
            swipeRefresh.isRefreshing = false
        }
        if (eventList.isEmpty()) presenter.getMatchList(League.id)
    }

    inner class Ui: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
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
                                addItemDecoration(DividerItemDecoration(ctx, HORIZONTAL))
                            }
                            progressBar = progressBar { gone() }.lparams{centerHorizontally()}
                        }
                    }
                }
            }
        }
    }

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
        eventList.addAll(matchList)
        adapter.notifyDataSetChanged()
    }
}