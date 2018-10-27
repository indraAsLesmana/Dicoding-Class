package com.tutor93.menampilkanarray.submission2.Event

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.model.Event
import org.jetbrains.anko.*

class EventAdapter(private val teamList: List<Event>) : RecyclerView.Adapter<EventAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder = TeamViewHolder(
        TeamUi().createView(AnkoContext.create(p0.context, p0))
    )

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(p0: TeamViewHolder, position: Int) {
        p0.bindItem(teamList[position])
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val homeClube: TextView = view.find(R.id.homeClubName)
        private val homeScore: TextView = view.find(R.id.homeScore)
        private val awayClube: TextView = view.find(R.id.awayClube)
        private val awayScore: TextView = view.find(R.id.awayScore)
        //private val dateEvent: TextView = view.find(R.id.dateEvent)

        fun bindItem(team: Event) {
            homeClube.text = team.strHomeTeam
            team.intHomeScore?.let { homeScore.text = it.toString() }
            awayClube.text = team.strAwayTeam
            team.intAwayScore?.let { awayScore.text = it.toString() }
            //dateEvent.text = team.dateEvent.toString()
        }
    }

    class TeamUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(matchParent, wrapContent)
                    orientation = LinearLayout.VERTICAL
                    /*textView {
                        id = R.id.dateEvent
                    }.lparams(matchParent, wrapContent)*/
                    linearLayout {
                        lparams(matchParent, wrapContent)
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 7f

                        textView {
                            id = R.id.homeClubName
                            textSize = 16f
                           /* gravity = Gravity.START
                            width = dip(0)
                            height = wrapContent*/
                        }.lparams {
                            weight = 2f
                        }
                        textView {
                            id = R.id.homeScore
                            /*width = dip(0)
                            height = wrapContent
                            gravity = Gravity.CENTER*/
                        }.lparams {
                            weight = 1f

                        }

                        textView {
                            text = "vs"
                           /* width = dip(0)
                            height = wrapContent
                            gravity = Gravity.CENTER*/
                        }.lparams {
                            weight = 1f
                        }

                        textView {
                            id = R.id.awayScore
                           /* width = dip(0)
                            height = wrapContent
                            gravity = Gravity.CENTER*/
                        }.lparams {
                            weight = 1f
                        }

                        textView {
                            id = R.id.awayClube
                            textSize = 16f
                           /* width = dip(0)
                            height = wrapContent
                            gravity = Gravity.END*/
                        }.lparams {
                            weight = 2f
                        }

                    }

                }
            }
        }

    }
}