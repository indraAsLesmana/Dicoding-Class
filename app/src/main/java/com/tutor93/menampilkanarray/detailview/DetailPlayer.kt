package com.tutor93.menampilkanarray.detailview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.Player
import kotlinx.android.synthetic.main.activity_detailplayer.*

class DetailPlayer: AppCompatActivity(), DetailPlayerView{
    override fun showPLayerDetail(data: Player) {
        tvDescPlayer.text = data.strDescriptionEN
        Picasso.get().load(data.strFanart1).into(imageView4)
        textView9.text = data.strWeight
        textView10.text = data.strHeight
        textView12.text = data.strPosition
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    private lateinit var player: Player
    private lateinit var presenter          : DetailPlayerPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailplayer)
        player = intent.getParcelableExtra("data")

        presenter = DetailPlayerPresenter(this, ApiRepository(), Gson())
        presenter.getPlayerDetail(player.idPlayer)
    }
}