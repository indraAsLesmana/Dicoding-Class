package com.tutor93.menampilkanarray.submission2.Event

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.MatchResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
) {
    fun getMatchList(leagueId: String?, isPastRequest: Boolean? = false) {
        /**
         * with courutines
         * */
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getMatchList(leagueId, isPastRequest)),
                    MatchResponse::class.java
                )
            }
            data.await().events?.let { view.showMatchList(it) }
            view.hideLoading()

        }
    }
}