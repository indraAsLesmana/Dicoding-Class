package com.tutor93.menampilkanarray.latihan1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.model.Item
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter(private val context: Context, private val Item: List<Item>, private val listener: (Item) -> Unit): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItem(
            items: Item,
            listener: (Item) -> Unit
        ) {
            itemView.name.text = items.name
            items.image.let { Glide.with(itemView.context).load(items.image).into(itemView.image) }
            itemView.setOnClickListener { listener(items) }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = Item.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(Item[p1], listener)
    }

}