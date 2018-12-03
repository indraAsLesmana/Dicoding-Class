package com.tutor93.menampilkanarray.main.match

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.MatchResponse
import com.tutor93.menampilkanarray.model.response.MatchesResponse
import com.tutor93.menampilkanarray.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {


    fun getMatchList(leagueId: String?, isPastRequest: Boolean? = false) {
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatchList(leagueId, isPastRequest)),
                    MatchResponse::class.java
                )
            }
            data.await().events?.let { view.showMatchList(it) }
            view.hideLoading()
        }
    }

    fun searchEvent(eventName: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.searchEvent(eventName)), MatchesResponse::class.java
            )
            uiThread {
                view.hideLoading()
                data.events?.let { eventList ->
                    view.showMatchList(eventList)
                }

            }
        }
    }
}