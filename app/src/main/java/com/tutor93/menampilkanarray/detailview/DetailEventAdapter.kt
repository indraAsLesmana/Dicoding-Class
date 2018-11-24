package com.tutor93.menampilkanarray.detailview

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.match.League
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
                isHomeScore->{ drawLayout(items) }
                isAwayScore->{ drawLayout(items, true) }
                isHomeYellowCard->{ drawLayout(items, false, true) }
                isAwayYellowCard->{ drawLayout(items, true, true) }
                isHomeRedCard->{ drawLayout(items,false, false, true) }
                isAwayRedCard->{ drawLayout(items,true, false, true) }
            }
        }

        private fun drawLayout(items: String, away: Boolean? = false, isYellowCard: Boolean? = false, isRedCard: Boolean? = false) {
            itemView.tvHomeGoal.gone()
            itemView.tvAwayGoal.gone()

            val data = items.removeSuffix(League.homeYellowCard.toString())
            val goalTime = data.split("'")
            itemView.tvTime.text = String.format("%s%s", goalTime[0], "'")
            var tvTargetView: TextView = itemView.tvHomeGoal
            if (away == true){
                tvTargetView = itemView.tvAwayGoal
            }
            tvTargetView.visible()
            tvTargetView.text = goalTime[1].removePrefix(":")
            val drawable: Drawable? = when (true) {
                isYellowCard -> { ContextCompat.getDrawable(itemView.context, R.drawable.ic_yellow_card) }
                isRedCard -> { ContextCompat.getDrawable(itemView.context, R.drawable.ic_red_card) }
                else -> { ContextCompat.getDrawable(itemView.context, R.drawable.ic_soccer_ball) }
            }
            tvTargetView.setCompoundDrawablesWithIntrinsicBounds(
                if (away == true) drawable else null,
                null,
                if (away == false) drawable else null,
                null
            )
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