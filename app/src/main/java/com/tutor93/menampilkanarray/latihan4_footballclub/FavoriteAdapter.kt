package com.tutor93.menampilkanarray.latihan4_footballclub

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R.id.*
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.invisible
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.toStringDateFormat
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FavoriteAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                cardElevation = 2f
                radius = 0f
                useCompatPadding = true
                relativeLayout {
                    imageView {
                        id = team_badge
                    }.lparams(width = dip(68), height = dip(68)){
                        topMargin = dip(8)
                        bottomMargin = dip(8)
                    }
                    imageView {
                        id = team_badge_2
                    }.lparams(width = dip(68), height = dip(68)) {
                        alignParentLeft()
                        alignParentTop()
                        leftMargin = dip(50)
                        topMargin = dip(8)
                        bottomMargin = dip(8)
                    }
                    textView {
                        id = team_name
                        textSize = 16f
                    }.lparams {
                        alignParentTop()
                        topMargin = dip(26)
                        alignParentRight()
                        rightMargin = matchParent
                        rightOf(team_badge_2)
                        leftMargin = dip(8)
                    }
                }.lparams(width = matchParent)
            }
        }
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(team_badge)
    private val teamBadge2: ImageView = view.find(team_badge_2)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        val event = Gson().fromJson<Event>(favorite.teamEvent, Event::class.java)
        if (favorite.teamEvent.isNullOrEmpty()){
            Picasso.get().load(favorite.teamBadge).into(teamBadge)
            teamBadge2.imageBitmap = null
            teamBadge2.invisible()
            teamName.text = favorite.teamName

        }else{
            teamBadge2.visible()
            Picasso.get().load(favorite.teamHomeBadge).into(teamBadge)
            Picasso.get().load(favorite.teamAwayBadge).into(teamBadge2)
            val isSchedule = event.intHomeScore == null
            if (isSchedule){
                teamName.text = event.strDate?.toStringDateFormat("dd/mm/yy", "E, dd MMM yyyy")
            }else{
                teamName.text = buildString {
                    append(event.strHomeTeam+" ")
                    append(event.intHomeScore)
                    append(" vs ")
                    append(event.intAwayScore)
                    append(" "+event.strAwayTeam)
                }
            }
        }
        itemView.setOnClickListener { listener(favorite) }
    }
}