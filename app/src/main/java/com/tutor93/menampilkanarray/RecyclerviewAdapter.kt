package com.tutor93.menampilkanarray

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerviewAdapter(private val context: Context, private val item: List<item>, private val listener: (item) -> Unit): RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItem(
            items: item,
            listener: (item) -> Unit
        ) {
            itemView.name.text = items.name
            items.image.let { Glide.with(itemView.context).load(items.image).into(itemView.image) }
            itemView.setOnClickListener { listener(items) }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
         ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, p0, false))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(item[p1], listener)
    }

}