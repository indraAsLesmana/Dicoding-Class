package com.tutor93.menampilkanarray.main.detail.detailmatch

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.base.League
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.database
import com.tutor93.menampilkanarray.formated
import com.tutor93.menampilkanarray.main.detail.DetailPresenter
import com.tutor93.menampilkanarray.main.detail.DetailView
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.model.Player
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.toStringDateFormat
import kotlinx.android.synthetic.main.activity_detail_lastevent.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailMatchActivity: AppCompatActivity(), DetailView {
    private lateinit var presenter  : DetailPresenter
    private lateinit var adapter    : DetailMatchAdapter
    private var allGoal             : MutableList<String> = mutableListOf()
    private var menuItem            : Menu? = null
    private var mTeam               : Team = Team()
    private var isFavorite          : Boolean = false
    private var isFavoriteTemp      : Boolean = false
    private var awayBadge           : String? = null
    private var homeBadge           : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_detail_lastevent)

        adapter = DetailMatchAdapter(this, allGoal) {}
        rvEventList.layoutManager = LinearLayoutManager(this)
        rvEventList.adapter       = adapter

        presenter = DetailPresenter(this, ApiRepository(), Gson())

        intent.getParcelableExtra<Event>("data").let { it ->
            mTeam.teamEvent = it
            mTeam.teamId    = it.idEvent
            mTeam.teamName  = it.strEvent
            mTeam.teamBadge = it.strThumb

            it.idHomeTeam?.let {
                presenter.getTeamDetail(it, League.homeScore)
            }
            it.idAwayTeam?.let {
                presenter.getTeamDetail(it, League.awayScore)
            }
            it.intHomeScore?.let { textView3.text = it.toString() }
            it.intAwayScore?.let { textView4.text = it.toString() }
            textView5.text = it.strHomeTeam?.substring(0,3)
            textView6.text = it.strAwayTeam?.substring(0,3)

            if (it.isNextMatch == true){
                layDetailContainer.displayedChild = 1
                tvDateMatch.text = buildString {
                    append("match start at\n")
                    append(it.strDate?.toStringDateFormat("dd/mm/yy", "E, dd MMM yyyy"))
                }
            }else{
                layDetailContainer  .displayedChild = 0
                tvDate.text         = it.strDate?.toStringDateFormat("dd/mm/yy", "E, dd MMM yyyy")
                val goalHome        = it.strHomeGoalDetails?.split(";")?.filter { !it.isEmpty() }
                val goalAway        = it.strAwayGoalDetails?.split(";")?.filter { !it.isEmpty() }
                val homeYellowCard  = it.strHomeYellowCards?.split(";")?.filter { !it.isEmpty() }
                val awayYellowCard  = it.strAwayYellowCards?.split(";")?.filter { !it.isEmpty() }
                val homeRedCard     = it.strHomeRedCards?.split(";")?.filter { !it.isEmpty() }
                val awayRedCard     = it.strAwayRedCards?.split(";")?.filter { !it.isEmpty() }
                allGoal.clear()
                goalHome        ?.forEach { allGoal.add(String.format("%s%s", it, League.homeScore)) }
                goalAway        ?.forEach { allGoal.add(String.format("%s%s", it, League.awayScore)) }
                homeYellowCard  ?.forEach { allGoal.add(String.format("%s%s", it, League.homeYellowCard)) }
                awayYellowCard  ?.forEach { allGoal.add(String.format("%s%s", it, League.awayYellowCard)) }
                homeRedCard     ?.forEach { allGoal.add(String.format("%s%s", it, League.homeRedCard)) }
                awayRedCard     ?.forEach { allGoal.add(String.format("%s%s", it, League.awayRedCard)) }

                allGoal.sort()
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem        = menu
        favoriteState   ()
        setFavorite     ()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.add_to_favorite -> {
                if (awayBadge.isNullOrEmpty() && homeBadge.isNullOrEmpty()){
                    snackbar(layDetailContainer, "Please wait, until all data loaded").show()
                    return true
                }
                if (isFavorite)
                    isFavorite = presenter.removeFavorite(layDetailContainer, mTeam)
                else
                    isFavorite = presenter.addFavorite(layDetailContainer, mTeam, true, awayBadge, homeBadge)
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showTeamLogo(url: String, into: Int) {
        when(into){
            League.awayScore -> {
                awayBadge = url
                Picasso.get().load(awayBadge).into(imageView2)
            }
            else -> {
                homeBadge = url
                Picasso.get().load(homeBadge).into(imageView)
            }
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to mTeam.teamId!!
                )
            val favorite = result.parseList(classParser<Favorite>())
            favorite.forEach {
                if (it.teamId?.equals(mTeam.teamId) == true) {
                    isFavorite = true
                    return@forEach
                }
            }
            isFavoriteTemp = isFavorite
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    override fun onBackPressed() {
        if (isFavoriteTemp != isFavorite) setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }

    override fun sendGetRequest(mTeam: Team) {}
    override fun showPLayerDetail(data: Player) {}
    override fun showTeamList(data: List<Team>) {}
    override fun showPlayerList(player: List<Player>) {}
}