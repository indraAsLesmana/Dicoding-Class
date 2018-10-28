package com.tutor93.menampilkanarray.submission2.Event

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.MatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
) {
    fun getMatchList(leagueId: String?, isPastRequest: Boolean? = false) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getMatchList(leagueId, isPastRequest?:false)), MatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                data.events?.let {
                    view.showMatchList(it)
                }

            }
        }
    }
}