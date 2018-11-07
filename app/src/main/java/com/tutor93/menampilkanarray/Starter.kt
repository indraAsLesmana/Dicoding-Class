package com.tutor93.menampilkanarray

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tutor93.menampilkanarray.submission3.Sub3Activity
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
                 * Latihan 5: Submmision 3
                 * */
                button("Submmision 3"){
                    onClick { startActivity<Sub3Activity>() }
                }.lparams(matchParent).performClick()
            }
        }
    }
}