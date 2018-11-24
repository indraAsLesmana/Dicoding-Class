package com.tutor93.menampilkanarray.ankoview

import android.graphics.drawable.ClipDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.gone
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class Ui : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(matchParent, matchParent)
                setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.white))
                orientation = LinearLayout.VERTICAL
                topPadding  = dip(16)
                leftPadding = dip(16)
                rightPadding= dip(16)

                swipeRefreshLayout {
                    id = R.id.matchViewSwiperefresh
                    setColorSchemeColors(
                        android.support.design.R.attr.colorAccent,
                        ContextCompat.getColor(ctx, android.R.color.holo_green_light),
                        ContextCompat.getColor(ctx, android.R.color.holo_orange_light),
                        ContextCompat.getColor(ctx, android.R.color.holo_red_light)
                    )
                    relativeLayout {
                        lparams(matchParent, matchParent)
                        recyclerView {
                            id = R.id.matchViewRecyclerview
                            lparams(matchParent, matchParent)
                            layoutManager = LinearLayoutManager(ctx)
                            addItemDecoration(DividerItemDecoration(ctx, ClipDrawable.HORIZONTAL))
                        }
                        progressBar {
                            id = R.id.matchViewProgress
                            gone()
                        }.lparams { centerHorizontally() }
                    }
                }
            }
        }
    }
}