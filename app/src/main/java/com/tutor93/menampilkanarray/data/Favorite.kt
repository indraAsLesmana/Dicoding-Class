package com.tutor93.menampilkanarray.data

data class Favorite(val id: Long?, val teamId: String?, val teamName: String?, val teamBadge: String?, val teamEvent: String? = null, val teamAwayBadge: String? = null, val teamHomeBadge: String? = null) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_EVENT: String = "TEAM_EVENT"
        const val TEAM_AWAY_BADGE: String = "TEAM_AWAY_BADGE"
        const val TEAM_HOME_BADGE: String = "TEAM_HOME_BADGE"
    }
}