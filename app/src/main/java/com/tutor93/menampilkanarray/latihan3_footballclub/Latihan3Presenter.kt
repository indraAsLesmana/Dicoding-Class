package com.tutor93.menampilkanarray.latihan3_footballclub

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.response.TeamResponse
import com.tutor93.menampilkanarray.model.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Latihan3Presenter(private val view: Latihan3View,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?){
        /*view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)), TeamResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data = data.teams)
            }
        }*/
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
                )
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}