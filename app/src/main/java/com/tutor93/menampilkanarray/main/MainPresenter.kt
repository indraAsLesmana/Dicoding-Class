package com.tutor93.menampilkanarray.main

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson) {

    fun getTeamList(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)), TeamResponse::class.java)
            uiThread {
                view.hideLoading()
                if (data.teams == null || data.teams.isEmpty()){
                    // show view empty data
                }else{
                    view.showTeamList(data.teams)
                }
            }
        }
    }

    fun searchTeam(teamName: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchTeam(teamName)), TeamResponse::class.java)
            uiThread {
                view.hideLoading()
                if (data.teams == null || data.teams.isEmpty()){
                    // show view empty data
                }else{
                    view.showTeamList(data.teams)
                }

            }
        }
    }

}