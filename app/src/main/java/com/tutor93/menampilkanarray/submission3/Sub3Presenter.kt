package com.tutor93.menampilkanarray.submission3

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Sub3Presenter(
    private val view: Sub3View,
    private val apiRepository: ApiRepository,
    private val gson: Gson) {

    fun getTeamList(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)), TeamResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data = data.teams)
            }
        }
    }

}