package com.tutor93.menampilkanarray.latihan4_footballclub

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.model.Team
import org.jetbrains.anko.*

class Latihan4Adapter(private val teamList: List<Team>, private val listener: (Team) -> Unit): RecyclerView.Adapter<Latihan4Adapter.TeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder = TeamViewHolder(
        TeamUi().createView(AnkoContext.create(p0.context, p0))
    )

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(p0: TeamViewHolder, position: Int) {
        p0.bindItem(teamList[position], listener)
    }

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)
        fun bindItem(
            team: Team,
            listener: (Team) -> Unit
        ) {
            Picasso.get().load(team.teamBadge).into(teamBadge)
            teamName.text = team.teamName
            itemView.setOnClickListener { listener(team) }
        }
    }

    class TeamUi: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(matchParent, wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView { id = R.id.team_badge }
                        .lparams {
                            width = dip(50)
                            height = dip(50)
                        }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{margin = dip(15)}
                }
            }
        }

    }
}