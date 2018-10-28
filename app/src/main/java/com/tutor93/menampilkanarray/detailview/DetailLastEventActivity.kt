package com.tutor93.menampilkanarray.detailview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.formated
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.submission2.Event.League
import kotlinx.android.synthetic.main.activity_detail_lastevent.*

class DetailLastEventActivity: AppCompatActivity(), DetailEventView{

    private lateinit var presenter: DetailEventPresenter
    private lateinit var adapter: DetailEventAdapter
    private var allGoal: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_detail_lastevent)


        adapter = DetailEventAdapter(this, allGoal) {}
        rvEventList.layoutManager = LinearLayoutManager(this)
        rvEventList.adapter = adapter
        presenter = DetailEventPresenter(this, ApiRepository(), Gson())

        intent.getParcelableExtra<Event>("data").let { it ->
            it.idHomeTeam?.let {
                presenter.getTeamDetail(it, League.homeScore)
            }
            it.idAwayTeam?.let {
                presenter.getTeamDetail(it, League.awayScore)
            }
            textView2.text = it.strDate?.formated()
            textView3.text = it.intHomeScore.toString()
            textView4.text = it.intAwayScore.toString()
            textView5.text = it.strHomeTeam?.substring(0,3)
            textView6.text = it.strAwayTeam?.substring(0,3)

            val goalHome = it.strHomeGoalDetails?.split(";")?.filter { !it.isEmpty() }
            val goalAway = it.strAwayGoalDetails?.split(";")?.filter { !it.isEmpty() }
            val homeYellowCard = it.strHomeYellowCards?.split(";")?.filter { !it.isEmpty() }
            val awayYellowCard = it.strAwayYellowCards?.split(";")?.filter { !it.isEmpty() }
            val homeRedCard = it.strHomeRedCards?.split(";")?.filter { !it.isEmpty() }
            val awayRedCard = it.strAwayRedCards?.split(";")?.filter { !it.isEmpty() }

            allGoal.clear()
            goalHome?.forEach { allGoal.add(String.format("%s%s", it, League.homeScore)) }
            goalAway?.forEach { allGoal.add(String.format("%s%s", it, League.awayScore)) }
            homeYellowCard?.forEach { allGoal.add(String.format("%s%s", it, League.homeYellowCard)) }
            awayYellowCard?.forEach { allGoal.add(String.format("%s%s", it, League.awayYellowCard)) }
            homeRedCard?.forEach { allGoal.add(String.format("%s%s", it, League.homeRedCard)) }
            awayRedCard?.forEach { allGoal.add(String.format("%s%s", it, League.awayRedCard)) }

            allGoal.sort()
            adapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home){
            finish()
            false
        }else{
            super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showTeamLogo(url: String, into: Int) {
        when(into){
            League.awayScore -> Picasso.get().load(url).into(imageView2)
            else -> Picasso.get().load(url).into(imageView)
        }
    }
}