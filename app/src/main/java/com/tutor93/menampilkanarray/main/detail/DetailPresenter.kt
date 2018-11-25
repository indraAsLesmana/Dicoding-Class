package com.tutor93.menampilkanarray.main.detail

import android.database.sqlite.SQLiteConstraintException
import android.view.View
import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.database
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.SearchPlayerResponse
import com.tutor93.menampilkanarray.model.response.TeamResponse
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
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

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if (data.teams == null || data.teams.isEmpty()){
                    // show empety data
                }else{
                    view.showTeamList(data.teams)
                }
            }
        }
    }

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

    fun getListPlayer(teamName: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchPlayer(teamName)),
                SearchPlayerResponse::class.java
            )

            uiThread {
                view.hideLoading()
                data.player?.let { playerList ->
                    view.showPlayerList(playerList)
                }
            }
        }
    }

    fun addFavorite(view: View, mTeam: Team, isMatch: Boolean? = false, awayBadge: String?= null, homeBadge:String?= null): Boolean{
        var isSuccess = false
        try {
            view.context.database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_ID to mTeam.teamId,
                    Favorite.TEAM_NAME to mTeam.teamName,
                    Favorite.TEAM_BADGE to mTeam.teamBadge,
                    Favorite.TEAM_EVENT to if (isMatch == true)Gson().toJson(mTeam.teamEvent) else null,
                    Favorite.TEAM_AWAY_BADGE to awayBadge,
                    Favorite.TEAM_HOME_BADGE to homeBadge)
            }
            isSuccess = true
            snackbar(view, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view, e.localizedMessage).show()
        }
        return isSuccess
    }

    fun removeFavorite(view: View, mTeam: Team): Boolean{
        var isSucces = true
        try {
            view.context.database.use {
                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to mTeam.teamId!!)
            }
            isSucces = false
            snackbar(view, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view, e.localizedMessage).show()
        }
        return isSucces
    }
}