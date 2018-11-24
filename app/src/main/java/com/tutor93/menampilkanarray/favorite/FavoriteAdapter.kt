package com.tutor93.menampilkanarray.favorite

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tutor93.menampilkanarray.R.id.*
import com.tutor93.menampilkanarray.ankoview.TeamUIMatch
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.invisible
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.toStringDateFormat
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*

class FavoriteAdapterMatch(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit) :
    RecyclerView.Adapter<FavoriteViewHolderMatch>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolderMatch {
        return FavoriteViewHolderMatch(
            TeamUIMatch().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolderMatch, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size
}

class FavoriteViewHolderMatch(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView    = view.find(team_badge)
    private val teamBadge2: ImageView   = view.find(team_badge_2)
    private val teamName: TextView      = view.find(team_name)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        val event = Gson().fromJson<Event>(favorite.teamEvent, Event::class.java)
        if (favorite.teamEvent.isNullOrEmpty()){
            Picasso.get().load(favorite.teamBadge).into(teamBadge)
            teamBadge2  .imageBitmap = null
            teamBadge2  .invisible()
            teamName    .text = favorite.teamName
        } else {
            teamBadge2.visible()
            Picasso.get()   .load(favorite.teamHomeBadge).into(teamBadge)
            Picasso.get()   .load(favorite.teamAwayBadge).into(teamBadge2)
            val isSchedule  = event.intHomeScore == null

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