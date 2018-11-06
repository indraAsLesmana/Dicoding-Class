package com.tutor93.menampilkanarray.detailview

import android.app.Activity
import android.database.sqlite.SQLiteConstraintException
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
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.database
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.submission2.Event.League
import com.tutor93.menampilkanarray.toStringDateFormat
import kotlinx.android.synthetic.main.activity_detail_lastevent.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailLastEventActivity: AppCompatActivity(), DetailEventView{
    private lateinit var presenter: DetailEventPresenter
    private lateinit var adapter: DetailEventAdapter
    private var allGoal: MutableList<String> = mutableListOf()
    private var menuItem: Menu? = null
    private var mTeam: Team = Team()
    private var isFavorite: Boolean = false
    private var isFavoriteTemp: Boolean = false

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
            mTeam.teamId = it.idEvent
            mTeam.teamName = it.strEvent
            mTeam.teamBadge = it.strThumb
            mTeam.teamEvent = it

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
                layDetailContainer.displayedChild = 0
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
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        favoriteState()
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addtoFavorite()
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
            League.awayScore -> Picasso.get().load(url).into(imageView2)
            else -> Picasso.get().load(url).into(imageView)
        }
    }


    private fun addtoFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_ID to mTeam.teamId,
                    Favorite.TEAM_NAME to mTeam.teamName,
                    Favorite.TEAM_BADGE to mTeam.teamBadge,
                    Favorite.TEAM_EVENT to Gson().toJson(mTeam.teamEvent))
            }
            isFavorite = true
            //snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            //snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to mTeam.teamId!!)
            }
            isFavorite = false
            //snackbar(swipeRefresh, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            //snackbar(swipeRefresh, e.localizedMessage).show()
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
}