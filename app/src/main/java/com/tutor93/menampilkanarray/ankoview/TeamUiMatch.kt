package com.tutor93.menampilkanarray.ankoview

import android.view.View
import android.view.ViewGroup
import com.tutor93.menampilkanarray.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamUIMatch : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                cardElevation    = 2f
                radius           = 0f
                useCompatPadding = true
                relativeLayout {
                    imageView {
                        id = R.id.team_badge
                    }.lparams(width = dip(68), height = dip(68)){
                        topMargin   = dip(8)
                        bottomMargin= dip(8)
                    }
                    imageView {
                        id = R.id.team_badge_2
                    }.lparams(width = dip(68), height = dip(68)) {
                        alignParentLeft()
                        alignParentTop()
                        leftMargin  = dip(50)
                        topMargin   = dip(8)
                        bottomMargin= dip(8)
                    }
                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams {
                        alignParentTop()
                        alignParentRight()
                        rightOf(R.id.team_badge_2)
                        topMargin   = dip(26)
                        rightMargin = matchParent
                        leftMargin  = dip(8)
                    }
                }.lparams(width = matchParent)
            }
        }
    }
}