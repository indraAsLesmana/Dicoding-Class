package com.tutor93.menampilkanarray

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tutor93.menampilkanarray.latihan2.Anko
import com.tutor93.menampilkanarray.latihan3_footballclub.Latihan3Activity
import com.tutor93.menampilkanarray.latihan4_footballclub.Latihan4Activity
import com.tutor93.menampilkanarray.main.MainActivity
import com.tutor93.menampilkanarray.submission1.SubOneActivity
import com.tutor93.menampilkanarray.submission2.SubTwoActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class Starter: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StarterUI().setContentView(this)
        supportActionBar?.title = "Dicoding latihan"
    }

    class StarterUI: AnkoComponent<Starter> {
        override fun createView(ui: AnkoContext<Starter>) = with(ui) {
            verticalLayout {
                padding = dip(16)

                /**
                 * Latihan 1: Manampilkan Array
                 * */
                button("Menampilkan Array"){
                    onClick { startActivity<MainActivity>() }
                    //attr(R.attr.textAllCaps) how to set this to false?
                }.lparams(matchParent)


                /**
                 * Latihan 2: Menarapkan Anko
                 * */
                button("Menerapkan Anko Commons dan Anko Layout"){
                    onClick { startActivity<Anko>() }
                }.lparams(matchParent)

                /**
                 * Submission 1:
                 * */
                button("Submission 1"){
                    onClick { startActivity<SubOneActivity>() }
                }.lparams(matchParent)

                /**
                 * Latihan 3: Football club
                 * */
                button("Latihan Football club"){
                    onClick { startActivity<Latihan3Activity>() }
                }.lparams(matchParent)

                /**
                 * Submission 2: Submission 2
                 * */
                button("Submission 2"){
                    onClick { startActivity<SubTwoActivity>() }
                }.lparams(matchParent)

                /**
                 * Latihan 4: Football club
                 * */
                button("Latihan Football club and DB"){
                    onClick { startActivity<Latihan4Activity>() }
                }.lparams(matchParent)


                /**
                 * Latihan 5: Submmision 3
                 * */
                button("Latihan Football club and DB"){
                    onClick { startActivity<com.tutor93.menampilkanarray.main.MainActivity>() }
                }.lparams(matchParent).performClick()
            }
        }
    }
}