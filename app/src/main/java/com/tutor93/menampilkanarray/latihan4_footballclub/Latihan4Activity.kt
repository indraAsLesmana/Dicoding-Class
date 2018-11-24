package com.tutor93.menampilkanarray.latihan4_footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tutor93.menampilkanarray.R
import kotlinx.android.synthetic.main.activity_home.*

class Latihan4Activity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favorites->{
                    loadFavoriteFragment(savedInstanceState)
                }
                R.id.teams ->{
                    loadTeamsFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.teams
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }

    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        /*if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }*/
    }
}