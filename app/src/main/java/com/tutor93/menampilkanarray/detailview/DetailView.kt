package com.tutor93.menampilkanarray.detailview

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.model.Team
import kotlinx.android.synthetic.main.activity_detailview.*

class DetailView: AppCompatActivity(), DetailTeamView{

    private lateinit var presenter          : DetailTeamPresenter

    private var mTeam           : Team = Team()
    private var isFavorite      : Boolean = false
    private var isFavoriteTemp  : Boolean = false
    private var menuItem        : Menu? = null
    private var mAdapter        = DetailViewPagerAdapter(supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailview)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        mTeam.teamId = intent.getStringExtra("data")


        vpContent.adapter = mAdapter
        vpContent.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tbLayout))
        tbLayout.setupWithViewPager(vpContent)

        presenter = DetailTeamPresenter(this, ApiRepository(), Gson())
        mTeam.teamId?.let { presenter.getTeamDetail(it) }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamList(data: List<Team>) {
        mTeam = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)

        //swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(imageView3)

        //teamDescription.text    = data[0].teamDescription
        teamName.text           = data[0].teamName
        teamFormedYear.text     = data[0].teamFormedYear
        teamStadium.text        = data[0].teamStadium

        initTeamData()
    }

    private fun initTeamData() {
        val frag = mAdapter.getRegisteredFragment(0)
        (frag as DetailViewFrag1).sendGetRequest(mTeam)
    }

    interface sendData{
        fun teamData(team: Team)
    }
}