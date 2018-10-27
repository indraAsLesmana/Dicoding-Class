package com.tutor93.menampilkanarray.submission2

import com.tutor93.menampilkanarray.model.Event

interface SubTwoView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(matchList: List<Event>?)
}