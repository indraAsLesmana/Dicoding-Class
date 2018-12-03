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

    fun searchPlayer(playerName: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchplayers.php")
            .appendQueryParameter("t", playerName)
            .build()
            .toString()
    }

    fun searchTeam(teamName: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchteams.php")
            .appendQueryParameter("t", teamName)
            .build()
            .toString()
    }

    fun searchEvent(eventName: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchevents.php")
            .appendQueryParameter("e", eventName)
            .build()
            .toString()
    }

    fun playerDetailById(playerId: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupplayer.php")
            .appendQueryParameter("id", playerId)
            .build()
            .toString()
    }
}