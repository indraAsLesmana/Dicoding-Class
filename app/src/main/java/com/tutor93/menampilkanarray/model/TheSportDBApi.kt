package com.tutor93.menampilkanarray.model

import android.net.Uri
import com.tutor93.menampilkanarray.BuildConfig

object TheSportDBApi {
    fun getMatchList(leagueId: String?, isPastRequest: Boolean?): String {
        return buildString {
            append(BuildConfig.BASE_URL)
            append("api/")
            append("v1/")
            append("json/")
            append(BuildConfig.TSDB_API_KEY)
            append("/events${if (isPastRequest == true) "past" else "next"}league.php?")
            append("id=$leagueId")
        }
    }

    fun getTeamDetail(teamId: String?): String {
        /*return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()*/
        return buildString {
            append(BuildConfig.BASE_URL)
            append("api/")
            append("v1/")
            append("json/")
            append(BuildConfig.TSDB_API_KEY)
            append("/lookupteam.php?")
            append("id=$teamId")
        }
    }
}