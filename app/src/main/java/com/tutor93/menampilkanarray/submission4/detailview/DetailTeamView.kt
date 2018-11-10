package com.tutor93.menampilkanarray.submission4.detailview

import com.tutor93.menampilkanarray.model.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}