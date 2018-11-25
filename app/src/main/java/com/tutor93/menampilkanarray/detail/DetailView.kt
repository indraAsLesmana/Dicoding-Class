package com.tutor93.menampilkanarray.detail

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamLogo(url: String, into: Int)
}