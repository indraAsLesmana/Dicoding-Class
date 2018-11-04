package com.tutor93.menampilkanarray.detailview

import com.tutor93.menampilkanarray.model.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}