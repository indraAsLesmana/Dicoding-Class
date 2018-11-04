package com.tutor93.menampilkanarray.latihan4_footballclub

import com.tutor93.menampilkanarray.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}