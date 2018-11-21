package com.tutor93.menampilkanarray.detailview

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
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.latihan3_footballclub.Latihan3Adapter
import com.tutor93.menampilkanarray.model.Player
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class DetailViewFrag2: Fragment(), DetailTeamViewFragment {
    private lateinit var progressBar    : ProgressBar
    private lateinit var listiTeam      : RecyclerView
    private lateinit var adapter        : DetailViewPlayerAdapter
    private lateinit var presenter      : DetailTeamPresenter
    private var playerList              = mutableListOf<Player>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return Ui().createView(AnkoContext.create(ctx, container!!, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adapter = DetailViewPlayerAdapter(playerList)
        //listiTeam.adapter = adapter
        presenter = DetailTeamPresenter(this, ApiRepository(), Gson())
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
        adapter = DetailViewPlayerAdapter(playerList)
        listiTeam.adapter = adapter

    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showTeamList(data: List<Team>) {}
}