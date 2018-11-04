package com.tutor93.menampilkanarray.latihan4_footballclub

import android.content.Context
import android.os.Bundle
import android.support.design.R
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.detailview.DetailTeam
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.invisible
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class TeamsFragment: Fragment(), AnkoComponent<Context>, TeamsView {
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private var teamsList: MutableList<Team> = mutableListOf()
    private lateinit var listiTeam: RecyclerView
    private lateinit var adapter: Latihan4Adapter
    private lateinit var presenter: Latihan4Presenter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**
         * initialData
         * */
        adapter = Latihan4Adapter(teamsList){
            startActivity<DetailTeam>("data" to it)
        }
        listiTeam.adapter = adapter
        presenter = Latihan4Presenter(this, ApiRepository(), Gson())
        val spinerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(
            com.tutor93.menampilkanarray.R.array.league))
        spinner.adapter = spinerAdapter

        /**
         * listener
         * */
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getTeamList(spinner.selectedItem.toString())
            }
        }
        swipeRefresh.onRefresh {
            showLoading()
            presenter.getTeamList(spinner.selectedItem.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        /**
         * initialView
         * */
        linearLayout {
            lparams(matchParent, matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeColors(
                    R.attr.colorAccent,
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
    }

    override fun hideLoading() {
        listiTeam.visible()
        progressBar.gone()
    }
    override fun showLoading() {
        listiTeam.invisible()
        if (!swipeRefresh.isRefreshing) progressBar.visible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teamsList.clear()
        teamsList.addAll(data)
        adapter.notifyDataSetChanged()
    }
}