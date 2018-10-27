package com.tutor93.menampilkanarray.latihan3_footballclub

import com.tutor93.menampilkanarray.model.Team

interface Latihan3View {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}