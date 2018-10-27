package com.tutor93.menampilkanarray.submission2

import com.tutor93.menampilkanarray.model.Team

interface SubTwoView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(matchList: List<Team>)
}