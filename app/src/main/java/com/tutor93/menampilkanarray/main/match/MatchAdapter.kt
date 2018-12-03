package com.tutor93.menampilkanarray.main.match

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.tutor93.menampilkanarray.*
import com.tutor93.menampilkanarray.model.Event
import org.jetbrains.anko.*
import java.util.*

class MatchAdapter(private val teamList: List<Event>, private val listener: Listener) : RecyclerView.Adapter<MatchAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder = TeamViewHolder(
        TeamUi().createView(AnkoContext.create(p0.context, p0))
    )

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(p0: TeamViewHolder, position: Int) {
        p0.bindItem(teamList[position], listener)
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val homeClube: TextView     = view.find(R.id.homeClubName)
        private val homeScore: TextView     = view.find(R.id.homeScore)
        private val awayClube: TextView     = view.find(R.id.awayClube)
        private val awayScore: TextView     = view.find(R.id.awayScore)
        private val dateEvent: TextView     = view.find(R.id.dateEvent)
        private val layEvent: LinearLayout  = view.find(R.id.layEvent)

        fun bindItem(
            event: Event,
            listener: Listener
        ) {
            homeClube.text = event.strHomeTeam
            event.intHomeScore?.let { homeScore.text = it.toString() }
            awayClube.text = event.strAwayTeam
            event.intAwayScore?.let { awayScore.text = it.toString() }
            layEvent.setOnClickListener { listener.onEventClicked(event) }

            if (Date().before(event.dateEvent)) {
                dateEvent.text = event.dateEvent?.formated()
                dateEvent.compoundDrawablePadding = 8
                dateEvent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_today_black_24dp, 0)
                dateEvent.setOnClickListener { listener.onDateClicked(event) }
            }else{
                dateEvent.text = event.dateEvent?.formated()
            }
        }
    }

    class TeamUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(matchParent, dip(66))
                    isClickable = true
                    orientation = LinearLayout.VERTICAL
                    textView {
                        id = R.id.dateEvent
                        backgroundResource = ctx.selectableItemBackgroundResource
                        textColor = ContextCompat.getColor(context, R.color.colorAccent)

                    }.lparams{
                        width = wrapContent
                        height = wrapContent
                        topMargin = dip(4)
                        bottomMargin = dip(4)
                        gravity = Gravity.CENTER + Gravity.TOP
                    }
                    linearLayout {
                        id = R.id.layEvent
                        backgroundResource = ctx.selectableItemBackgroundResource
                        gravity = Gravity.BOTTOM
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 9f
                        textView {
                            id = R.id.homeClubName
                            textSize = 16f
                            gravity = Gravity.START
                            ellipsize = TextUtils.TruncateAt.MIDDLE
                            singleLine = true
                        }.lparams {
                            weight = 3f
                            width = dip(0)
                            height = wrapContent
                        }
                        textView {
                            id = R.id.homeScore
                            gravity = Gravity.CENTER
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = 18f
                        }.lparams {
                            weight = 1f
                            width = dip(0)
                            height = wrapContent
                        }

                        textView {
                            text = "vs"
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                            width = dip(0)
                            height = wrapContent
                        }

                        textView {
                            id = R.id.awayScore
                            gravity = Gravity.CENTER
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = 18f
                        }.lparams {
                            weight = 1f
                            width = dip(0)
                            height = wrapContent
                        }

                        textView {
                            id = R.id.awayClube
                            textSize = 16f
                            gravity = Gravity.END
                            ellipsize = TextUtils.TruncateAt.MIDDLE
                            singleLine = true
                        }.lparams {
                            weight = 3f
                            width = dip(0)
                            height = wrapContent
                        }
                    }.lparams{
                        width = matchParent
                        height = matchParent
                        bottomMargin = dip(8)
                    }
                }
            }
        }
    }

    interface Listener {
        fun onDateClicked(data: Event)
        fun onEventClicked(data: Event)
    }
}