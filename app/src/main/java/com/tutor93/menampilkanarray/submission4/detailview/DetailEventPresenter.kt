package com.tutor93.menampilkanarray.submission4.detailview

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailEventPresenter(private val view: DetailEventView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getTeamDetail(teamId: String, into: Int) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)), TeamResponse::class.java
            )
            uiThread {
                view.hideLoading()
                data.teams?.let {
                    if (it.isNotEmpty() && !it[0].teamBadge.isNullOrEmpty()){
                        view.showTeamLogo(it[0].teamBadge!!, into)
                    }
                }
            }
        }
    }
}