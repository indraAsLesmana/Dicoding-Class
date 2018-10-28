package com.tutor93.menampilkanarray.detailview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.submission2.Event.League
import com.tutor93.menampilkanarray.visible
import kotlinx.android.synthetic.main.item_goal.view.*

class DetailEventAdapter(private val context: Context, private val Item: List<String>, private val listener: (String) -> Unit): RecyclerView.Adapter<DetailEventAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItem(
            items: String,
            listener: (String) -> Unit
        ) {
            val isHomeScore = items.endsWith(League.homeScore.toString())
            val isAwayScore = items.endsWith(League.awayScore.toString())
            val isHomeYellowCard = items.endsWith(League.homeYellowCard.toString())
            val isAwayYellowCard = items.endsWith(League.awayYellowCard.toString())
            val isHomeRedCard = items.endsWith(League.homeRedCard.toString())
            val isAwayRedCard = items.endsWith(League.awayRedCard.toString())

            when(true){
                isHomeScore->{
                    val data = items.removeSuffix(League.homeScore.toString())
                    val goalTime = data.split("'")
                    itemView.tvTime.text = String.format("%s%s", goalTime[0], "'")
                    itemView.tvHomeGoal.visible()
                    itemView.tvHomeGoal.text = goalTime[1].removePrefix(":")
                    itemView.tvHomeGoal.setCompoundDrawablesWithIntrinsicBounds(null,null,ContextCompat.getDrawable(itemView.context, R.drawable.ic_soccer_ball), null)
                }
                isAwayScore->{
                    val data = items.removeSuffix(League.awayScore.toString())
                    val goalTime = data.split("'")
                    itemView.tvTime.text = String.format("%s%s", goalTime[0], "'")
                    itemView.tvAwayGoal.visible()
                    itemView.tvAwayGoal.text = goalTime[1].removePrefix(":")
                    itemView.tvAwayGoal.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(itemView.context, R.drawable.ic_soccer_ball),null, null, null)
                }
                isHomeYellowCard->{
                    val data = items.removeSuffix(League.homeYellowCard.toString())
                    val goalTime = data.split("'")
                    itemView.tvTime.text = String.format("%s%s", goalTime[0], "'")
                    itemView.tvHomeGoal.visible()
                    itemView.tvHomeGoal.text = goalTime[1].removePrefix(":")
                    itemView.tvHomeGoal.setCompoundDrawablesWithIntrinsicBounds(null,null,ContextCompat.getDrawable(itemView.context, R.drawable.ic_yellow_card), null)
                }
                isAwayYellowCard->{
                    val data = items.removeSuffix(League.awayYellowCard.toString())
                    val goalTime = data.split("'")
                    itemView.tvTime.text = String.format("%s%s", goalTime[0], "'")
                    itemView.tvAwayGoal.visible()
                    itemView.tvAwayGoal.text = goalTime[1].removePrefix(":")
                    itemView.tvAwayGoal.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(itemView.context, R.drawable.ic_yellow_card),null, null, null)
                }
                isHomeRedCard->{
                    val data = items.removeSuffix(League.homeRedCard.toString())
                    val goalTime = data.split("'")
                    itemView.tvTime.text = String.format("%s%s", goalTime[0], "'")
                    itemView.tvHomeGoal.visible()
                    itemView.tvHomeGoal.text = goalTime[1].removePrefix(":")
                    itemView.tvHomeGoal.setCompoundDrawablesWithIntrinsicBounds(null,null,ContextCompat.getDrawable(itemView.context, R.drawable.ic_red_card), null)
                }
                isAwayRedCard->{
                    val data = items.removeSuffix(League.awayYellowCard.toString())
                    val goalTime = data.split("'")
                    itemView.tvTime.text = String.format("%s%s", goalTime[0], "'")
                    itemView.tvAwayGoal.visible()
                    itemView.tvAwayGoal.text = goalTime[1].removePrefix(":")
                    itemView.tvAwayGoal.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(itemView.context, R.drawable.ic_red_card),null,null, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_goal,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = Item.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(Item[p1], listener)
    }
}