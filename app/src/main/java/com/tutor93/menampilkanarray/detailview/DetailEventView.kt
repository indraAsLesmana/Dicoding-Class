package com.tutor93.menampilkanarray.detailview

interface DetailEventView {
    fun showLoading()
    fun hideLoading()
    fun showTeamLogo(url: String, into: Int)
}