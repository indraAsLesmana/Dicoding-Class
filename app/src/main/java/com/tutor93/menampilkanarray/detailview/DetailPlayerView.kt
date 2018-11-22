package com.tutor93.menampilkanarray.detailview

import com.tutor93.menampilkanarray.model.Player

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPLayerDetail(data: Player)
}