package com.tutor93.menampilkanarray.detailview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutor93.menampilkanarray.R

class DetailEventAdapter(private val context: Context, private val Item: List<String>, private val listener: (String) -> Unit): RecyclerView.Adapter<DetailEventAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItem(
            items: String,
            listener: (String) -> Unit
        ) {
            /*itemView.name.text = items.name
            items.image.let { Glide.with(itemView.context).load(items.image).into(itemView.image) }
            itemView.setOnClickListener { listener(items) }*/

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