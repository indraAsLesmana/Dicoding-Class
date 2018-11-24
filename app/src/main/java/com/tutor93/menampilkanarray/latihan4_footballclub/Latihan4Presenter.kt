package com.tutor93.menampilkanarray.latihan4_footballclub

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.response.TeamResponse
import com.tutor93.menampilkanarray.model.TheSportDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Latihan4Presenter(private val view: TeamsView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson) {
    fun getTeamList(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)), TeamResponse::class.java)

            uiThread {
                view.hideLoading()

                //view.showTeamList(data = data.teams)
                if (data.teams == null || data.teams.isEmpty()){
                    // show empety data
                }else{
                    view.showTeamList(data.teams)
                }
            }
        }
    }
}