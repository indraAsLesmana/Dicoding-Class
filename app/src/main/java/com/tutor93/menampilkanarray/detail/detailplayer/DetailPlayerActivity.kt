package com.tutor93.menampilkanarray.detail.detailplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.detail.DetailPresenter
import com.tutor93.menampilkanarray.detail.DetailView
import com.tutor93.menampilkanarray.model.Player
import com.tutor93.menampilkanarray.model.Team
import kotlinx.android.synthetic.main.activity_detailplayer.*

class DetailPlayerActivity: AppCompatActivity(), DetailView {
    private lateinit var player: Player
    private lateinit var presenter          : DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailplayer)
        player = intent.getParcelableExtra("data")

        presenter = DetailPresenter(this, ApiRepository(), Gson())
        presenter.getPlayerDetail(player.idPlayer)
    }

    override fun showPLayerDetail(data: Player) {
        tvDescPlayer.text   = data.strDescriptionEN
        textView9.text      = data.strWeight
        textView10.text     = data.strHeight
        textView12.text     = data.strPosition
        Picasso.get().load(data.strFanart1).into(imageView4)
    }

    override fun showLoading() {}
    override fun hideLoading() {}
    override fun showTeamLogo(url: String, into: Int) {}
    override fun showTeamList(data: List<Team>) {}
    override fun showPlayerList(player: List<Player>) {}
    override fun sendGetRequest(mTeam: Team) {}
}