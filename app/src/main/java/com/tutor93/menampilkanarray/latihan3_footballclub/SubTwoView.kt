package com.tutor93.menampilkanarray.latihan3_footballclub

import com.tutor93.menampilkanarray.model.Team

interface SubTwoView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}