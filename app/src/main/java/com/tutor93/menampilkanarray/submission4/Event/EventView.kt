package com.tutor93.menampilkanarray.submission4.Event

import com.tutor93.menampilkanarray.model.Event

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(matchList: List<Event>)
}