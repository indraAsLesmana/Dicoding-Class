package com.tutor93.menampilkanarray.main

import com.tutor93.menampilkanarray.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)

}