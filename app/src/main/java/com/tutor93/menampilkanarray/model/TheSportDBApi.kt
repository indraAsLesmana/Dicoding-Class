package com.tutor93.menampilkanarray.model

import android.net.Uri
import com.tutor93.menampilkanarray.BuildConfig

object TheSportDBApi {
    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l", league)
            .build()
            .toString()
    }

    fun getMatchList(leagueId: String?, isPastRequest: Boolean?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("events${if (isPastRequest == true) "past" else "next"}league.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
    }

    fun getTeamDetail(teamId: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
    }

    fun searchPlayer(teamName: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchplayers.php")
            .appendQueryParameter("t", teamName)
            .build()
            .toString()
    }
}