package com.tutor93.menampilkanarray.main.detail.detailplayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.main.detail.DetailPresenter
import com.tutor93.menampilkanarray.main.detail.DetailView
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.model.Player
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class DetailPlayerListFrag: Fragment(), DetailView {
    private lateinit var progressBar    : ProgressBar
    private lateinit var listiTeam      : RecyclerView
    private lateinit var adapter        : DetailPlayerAdapter
    private lateinit var presenter      : DetailPresenter
    private var playerList              = mutableListOf<Player>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return Ui().createView(AnkoContext.create(ctx, container!!, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adapter = DetailPlayerAdapter(playerList)
        //listiTeam.adapter = adapter
        presenter = DetailPresenter(this, ApiRepository(), Gson())
    }

    inner class Ui : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                relativeLayout {
                    lparams(matchParent, matchParent)

                    listiTeam = recyclerView {
                        lparams(matchParent, matchParent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                    progressBar = progressBar {}.lparams { centerHorizontally() }
                }
            }
        }
    }

    override fun sendGetRequest(mTeam: Team) {
        mTeam.teamName?.let { presenter.getListPlayer(it) }
    }

    override fun showPlayerList(player: List<Player>) {
        playerList.addAll(player)
        adapter = DetailPlayerAdapter(playerList) {
            startActivity<DetailPlayerActivity>("data" to it)
        }
        listiTeam.adapter = adapter

    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showTeamList(data: List<Team>) {}
    override fun showTeamLogo(url: String, into: Int) {}
    override fun showPLayerDetail(data: Player) {}
}