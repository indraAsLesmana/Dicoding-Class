package com.tutor93.menampilkanarray.model

import android.net.Uri
import com.tutor93.menampilkanarray.BuildConfig

object TheSportDBApi {
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
}