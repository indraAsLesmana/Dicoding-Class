package com.tutor93.menampilkanarray.detail

import com.tutor93.menampilkanarray.model.Player
import com.tutor93.menampilkanarray.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamLogo(url: String, into: Int)

    /*detail team*/
    fun showTeamList(data: List<Team>)
    fun showPlayerList(player: List<Player>)
    fun showPLayerDetail(data: Player)

}