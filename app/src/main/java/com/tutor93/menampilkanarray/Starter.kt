package com.tutor93.menampilkanarray

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutor93.menampilkanarray.latihan1.MainActivity
import com.tutor93.menampilkanarray.latihan2.Anko
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
            }
        }
    }
}