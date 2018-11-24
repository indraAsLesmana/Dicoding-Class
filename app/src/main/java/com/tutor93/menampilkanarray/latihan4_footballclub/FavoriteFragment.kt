package com.tutor93.menampilkanarray.latihan4_footballclub

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.ankoview.UiFavoriteItem
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.database
import com.tutor93.menampilkanarray.detailview.DetailLastEventActivity
import com.tutor93.menampilkanarray.detailview.DetailView
import com.tutor93.menampilkanarray.model.Event
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivityForResult

class FavoriteFragment: Fragment(){
    private lateinit var adapter        : FavoriteAdapter
    private lateinit var listEvent      : RecyclerView
    private lateinit var swipeRefresh   : SwipeRefreshLayout
    private var favorites               : MutableList<Favorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteAdapter(favorites){
            if (it.teamEvent?.isNotEmpty() == true){
                startActivityForResult<DetailLastEventActivity>(102, "data" to Gson().fromJson(it.teamEvent, Event::class.java))
            }else{
                startActivityForResult<DetailView>(101, "data" to "${it.teamId}")
            }
        }
        listEvent.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode     == 101
            || requestCode  == 102
            && resultCode   == -1 ){
            showFavorite()
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view     = UiFavoriteItem().createView(AnkoContext.create(ctx, container!!, false))
        swipeRefresh = view.find(R.id.favoriteViewSwiperefresh)
        listEvent    = view.find(R.id.favoriteViewSRecyclerview)
        return view
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>()).filter { it.teamAwayBadge == null }
            favorites   .clear()
            favorites   .addAll(favorite)
            adapter     .notifyDataSetChanged()
        }
    }

}