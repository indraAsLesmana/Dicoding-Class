package com.tutor93.menampilkanarray.submission4.Event

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.MatchResponse
import com.tutor93.menampilkanarray.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
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
}