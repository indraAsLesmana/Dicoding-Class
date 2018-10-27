package com.tutor93.menampilkanarray.submission2.Event

import com.tutor93.menampilkanarray.model.Event

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(matchList: List<Event>)
}