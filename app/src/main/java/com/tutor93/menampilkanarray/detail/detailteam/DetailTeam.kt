package com.tutor93.menampilkanarray.detail.detailteam

import android.app.Activity
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.database
import com.tutor93.menampilkanarray.detailview.*
import com.tutor93.menampilkanarray.model.Player
import com.tutor93.menampilkanarray.model.Team
import kotlinx.android.synthetic.main.activity_detailview.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailTeam: AppCompatActivity(), DetailTeamView {
    private var mTeam           : Team = Team()
    private var isFavorite      : Boolean = false
    private var isFavoriteTemp  : Boolean = false
    private var menuItem        : Menu? = null
    private var mAdapter        = DetailViewPagerAdapter(supportFragmentManager)

    private lateinit var presenter  : DetailTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailview)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        mTeam.teamId = intent.getStringExtra("data")

        vpContent   .adapter = mAdapter
        vpContent   .addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tbLayout))
        tbLayout    .setupWithViewPager(vpContent)
        presenter   = DetailTeamPresenter(this, ApiRepository(), Gson())
        mTeam.teamId?.let { presenter.getTeamDetail(it) }
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showTeamList(data: List<Team>) {
        mTeam = Team(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)

        Picasso.get()           .load(data[0].teamBadge).into(imageView3)
        teamName.text           = data[0].teamName
        teamFormedYear.text     = data[0].teamFormedYear
        teamStadium.text        = data[0].teamStadium
        initTeamData    ()
        initPlayerData  ()
    }

    private fun initPlayerData() {
        val frag = mAdapter.getRegisteredFragment(1)
        (frag as DetailViewFrag2).sendGetRequest(mTeam)
    }

    private fun initTeamData() {
        val frag = mAdapter.getRegisteredFragment(0)
        (frag as DetailViewFrag1).sendGetRequest(mTeam)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater    .inflate(R.menu.detail_menu, menu)
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
                if (isFavorite) removeFromFavorite() else addtoFavorite()
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addtoFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_ID   to mTeam.teamId,
                    Favorite.TEAM_NAME          to mTeam.teamName,
                    Favorite.TEAM_BADGE         to mTeam.teamBadge)
            }
            isFavorite = true
            snackbar(layDetailContainer, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(layDetailContainer, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to mTeam.teamId!!)
            }
            isFavorite = false
            snackbar(layDetailContainer, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(layDetailContainer, e.localizedMessage).show()
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
    override fun showPlayerList(player: List<Player>) {}

}