package com.tutor93.menampilkanarray.detailview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.Event
import kotlinx.android.synthetic.main.activity_detail_lastevent.*

class DetailLastEventActivity: AppCompatActivity(), DetailEventView{
    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showTeamLogo(url: String, into: Int) {
        when(into){
            badgeAway -> Picasso.get().load(url).into(imageView2)
            else -> Picasso.get().load(url).into(imageView)
        }
    }

    private val badgeHome = 188
    private val badgeAway = 189
    private lateinit var presenter: DetailEventPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_detail_lastevent)

        presenter = DetailEventPresenter(this, ApiRepository(), Gson())

        intent.getParcelableExtra<Event>("data").let { it ->
            it.idHomeTeam?.let {
                presenter.getTeamDetail(it, badgeHome)
            }
            it.idAwayTeam?.let {
                presenter.getTeamDetail(it, badgeAway)
            }

            textView3.text = it.intHomeScore.toString()
            textView4.text = it.intAwayScore.toString()
            textView5.text = it.strHomeTeam?.substring(0,3)
            textView6.text = it.strAwayTeam?.substring(0,3)

            val allGoal: MutableList<String> = mutableListOf()
            var goalHome = it.strHomeGoalDetails?.split(";")
            var goalAway = it.strAwayGoalDetails?.split(";")
            
            if (goalHome?.isNotEmpty() == true) allGoal.addAll(goalHome)
            if (goalAway?.isNotEmpty() == true) allGoal.addAll(goalAway)
            allGoal.sort()
            val a = allGoal


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

    private fun getImageUrl(id: String): String {
        return buildString {
            append("https://www.thesportsdb.com/images/media/league/fanart/xpwsrw")
            append(id)
            append(".jpg/preview").toString()
        }
    }
}