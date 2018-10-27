package com.tutor93.menampilkanarray.submission2.Event

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tutor93.menampilkanarray.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class NextEventFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
                        text = " NEXT FRAGMENT "
                    }.lparams{margin = dip(15)}
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val test = item?.itemId
        Toast.makeText(ctx, test.toString(), Toast.LENGTH_SHORT).show()
        return false
    }
}