package com.tutor93.menampilkanarray.latihan3_footballclub

import android.os.Bundle
import android.support.design.R.attr.colorAccent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.invisible
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SubTwoActivity: AppCompatActivity(), SubTwoView {
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listiTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var teamsList: MutableList<Team> = mutableListOf()
    private lateinit var adapter: SubTwoAdapter
    private lateinit var presenter: SubTwoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_submission_2)

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
                setColorSchemeColors(colorAccent,
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

        /**
         * initialData
         * */
        adapter = SubTwoAdapter(teamsList)
        listiTeam.adapter = adapter
        presenter = SubTwoPresenter(this, ApiRepository(), Gson())
        val spinerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.league))
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