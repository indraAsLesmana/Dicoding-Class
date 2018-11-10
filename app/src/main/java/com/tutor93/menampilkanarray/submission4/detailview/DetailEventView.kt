package com.tutor93.menampilkanarray.submission4.detailview

interface DetailEventView {
    fun showLoading()
    fun hideLoading()
    fun showTeamLogo(url: String, into: Int)
}