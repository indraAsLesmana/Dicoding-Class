package com.tutor93.menampilkanarray.main.match

import com.tutor93.menampilkanarray.model.Event

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(matchList: List<Event>)
}