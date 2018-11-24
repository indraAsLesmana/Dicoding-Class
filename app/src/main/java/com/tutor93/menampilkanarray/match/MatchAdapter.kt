package com.tutor93.menampilkanarray.match

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

class MatchAdapter(private val teamList: List<Event>, private val listener: (Event) -> Unit) : RecyclerView.Adapter<MatchAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder = TeamViewHolder(
        TeamUi().createView(AnkoContext.create(p0.context, p0))
    )

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(p0: TeamViewHolder, position: Int) {
        p0.bindItem(teamList[position], listener)
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val homeClube: TextView = view.find(R.id.homeClubName)
        private val homeScore: TextView = view.find(R.id.homeScore)
        private val awayClube: TextView = view.find(R.id.awayClube)
        private val awayScore: TextView = view.find(R.id.awayScore)
        private val dateEvent: TextView = view.find(R.id.dateEvent)

        fun bindItem(
            event: Event,
            listener: (Event) -> Unit
        ) {
            homeClube.text = event.strHomeTeam
            event.intHomeScore?.let { homeScore.text = it.toString() }
            awayClube.text = event.strAwayTeam
            event.intAwayScore?.let { awayScore.text = it.toString() }
            dateEvent.text = event.dateEvent?.formated()
            itemView.setOnClickListener { listener(event) }
        }
    }

    class TeamUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(matchParent, dip(56))
                    isClickable = true
                    backgroundResource = ctx.selectableItemBackgroundResource
                    orientation = LinearLayout.VERTICAL
                    textView {
                        id = R.id.dateEvent
                        gravity = Gravity.CENTER
                        textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        topMargin = dip(4)
                    }
                    linearLayout {
                        lparams(matchParent, matchParent)
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
                    }
                }
            }
        }

    }
}