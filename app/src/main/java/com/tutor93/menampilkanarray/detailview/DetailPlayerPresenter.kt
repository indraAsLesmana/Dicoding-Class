package com.tutor93.menampilkanarray.detailview

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.SearchPlayerResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPlayerPresenter(private val view: DetailPlayerView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getPlayerDetail(idPlayer: String?) {
            view.showLoading()
            doAsync {
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.playerDetailById(idPlayer)),
                    SearchPlayerResponse::class.java
                )

                uiThread {
                    view.hideLoading()
                    data?.playerDetail?.get(0)?.let {player ->
                        view.showPLayerDetail(player)
                    }
                }
            }
    }


}