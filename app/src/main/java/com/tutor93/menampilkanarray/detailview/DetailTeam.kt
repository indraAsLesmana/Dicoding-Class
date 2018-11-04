package com.tutor93.menampilkanarray.detailview

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.*
import com.tutor93.menampilkanarray.R.color.colorAccent
import com.tutor93.menampilkanarray.R.color.colorSecondaryText
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.model.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailTeam : AppCompatActivity(), DetailTeamView {
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView
    private lateinit var presenter: DetailTeamPresenter
    private var mTeam: Team = Team()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_teamdetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mTeam.teamId = intent.getStringExtra("data")

        /**
         * initial view
         * */
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            teamBadge = imageView {}.lparams(height = dip(75))

                            teamName = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = ContextCompat.getColor(context, colorAccent)
                            }.lparams {
                                topMargin = dip(5)
                            }

                            teamFormedYear = textView {
                                this.gravity = Gravity.CENTER
                            }

                            teamStadium = textView {
                                this.gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(context, colorSecondaryText)
                            }

                            teamDescription = textView().lparams {
                                topMargin = dip(20)
                            }
                        }
                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
                        }
                    }
                }
            }
        }

        /**
         * load data
         * */
        presenter = DetailTeamPresenter(this, ApiRepository(), Gson())
        /* saya telah cek di atas, mustahil jadi kosong :)*/
        presenter.getTeamDetail(mTeam.teamId!!)
        swipeRefresh.onRefresh { presenter.getTeamDetail(mTeam.teamId!!) }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        //menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                addtoFavorite()
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
                    Favorite.TEAM_ID to mTeam.teamId,
                    Favorite.TEAM_NAME to mTeam.teamName,
                    Favorite.TEAM_BADGE to mTeam.teamBadge)
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    override fun hideLoading() {
        progressBar.gone()
    }
    override fun showLoading() {
        if (!swipeRefresh.isRefreshing) progressBar.visible()
    }

    override fun showTeamList(data: List<Team>) {
        mTeam = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium
    }

}