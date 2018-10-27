package com.tutor93.menampilkanarray.submission2.Event

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutor93.menampilkanarray.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class PastEventFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return Ui().createView(AnkoContext.create(ctx, container!!, false))
    }

    class Ui: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(matchParent, matchParent)
                    setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.white))

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                        text = " PAST FRAGMENT "
                    }.lparams{margin = dip(15)}
                }
            }
        }
    }
}