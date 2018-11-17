package com.tutor93.menampilkanarray.submission3

import com.tutor93.menampilkanarray.model.Team

interface Sub3View {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)

}