package com.tutor93.menampilkanarray.submission1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tutor93.menampilkanarray.R
import kotlinx.android.synthetic.main.item_list.view.*

class SubOneAdapter(private val context: Context, private val item: List<ItemDetail>, private val listener: (ItemDetail) -> Unit): RecyclerView.Adapter<SubOneAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItem(
            items: ItemDetail,
            listener: (ItemDetail) -> Unit
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

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(item[p1], listener)
    }

}