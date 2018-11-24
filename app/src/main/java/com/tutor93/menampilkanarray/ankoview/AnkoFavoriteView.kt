package com.tutor93.menampilkanarray.ankoview

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.tutor93.menampilkanarray.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class UiFavoriteItem : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams (width = matchParent, height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)
                backgroundColor = Color.WHITE

                swipeRefreshLayout {
                    id = R.id.favoriteViewSwiperefresh
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    recyclerView {
                        id = R.id.favoriteViewSRecyclerview
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }
}