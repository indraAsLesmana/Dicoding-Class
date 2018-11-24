package com.tutor93.menampilkanarray.detailview

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.SearchPlayerResponse
import com.tutor93.menampilkanarray.model.response.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailTeamPresenter(private val view: DetailTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {
    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if (data.teams == null || data.teams.isEmpty()){
                    // show empety data
                }else{
                    view.showTeamList(data.teams)
                }
            }
        }
    }

    fun getListPlayer(teamName: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchPlayer(teamName)),
                SearchPlayerResponse::class.java
            )

            uiThread {
                view.hideLoading()
                data.player?.let { playerList ->
                    view.showPlayerList(playerList)
                }
            }
        }
    }
}