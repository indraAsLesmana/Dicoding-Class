package com.tutor93.menampilkanarray.submission2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.jsonString
import com.tutor93.menampilkanarray.model.League
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.model.response.LeagueResponse

class SubTwoActivity: AppCompatActivity(), SubTwoView{
    private var leagueList: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_footballmatch)

        // TODO 1. load local Json add to list field just 10 data
        val alllistLeague = Gson().fromJson(
            jsonString("list_league.json"),
            LeagueResponse::class.java
        ).leagues as MutableList<League>

        leagueList = alllistLeague.asSequence().take(10).toMutableList()

    }

    // TODO 2. make leagueList as optiomMenu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        leagueList.forEach {
            menu.add(0, it.idLeague?.toInt()?:0, 0, it.strLeague)
        }
        return true
    }

    // TODO 3. hit leagueList here
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //
        return true
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMatchList(matchList: List<Team>) {
    }

}