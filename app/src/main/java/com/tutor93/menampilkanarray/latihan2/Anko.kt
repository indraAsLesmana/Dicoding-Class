package com.tutor93.menampilkanarray.latihan2

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.postDelayed
import com.google.android.material.snackbar.Snackbar
import com.tutor93.menampilkanarray.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class Anko : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        supportActionBar?.title = "Anko"
    }

    class MainActivityUI: AnkoComponent<Anko> {
        override fun createView(ui: AnkoContext<Anko>) = with(ui) {
            verticalLayout {
                padding = dip(16)

                /**
                 * EditText:
                 * */
                val name = editText{
                    hint = "What's your name?"
                }

                /**
                 * Button Show Toast:
                 * */
                button("Say Hello"){
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE
                    onClick { toast("Hello, ${name.text}!") }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                /**
                 * Button Show Alert:
                 * */
                button("Show Alert"){
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE
                    onClick { _ ->
                        alert("Happy Coding!", "Hello, ${name.text}!") {
                            yesButton { toast("Oh…") }
                            noButton {}
                        }.show()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                /**
                 * Button Show Selector:
                 * */
                button("Show Selector"){
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE

                    onClick {
                        val club = listOf("Barcelona", "Real Madrid", "Bayern Munchen", "Liverpool")
                        selector("Hello, ${name.text}! What's football club do you love?", club) { _, i ->
                            toast("So you love ${club[i]}, right?")
                        }
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                /**
                 * Show Snackbar:
                 * */
                button("Show Snackbar"){
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE
                    onClick {
                        Snackbar.make(this@button, "Hello, ${name.text}!", Snackbar.LENGTH_SHORT).show()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                /**
                 * Button Show Progress Bar:
                 * */
                button("Show Progress Bar") {
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE
                    onClick {
                        val progress = indeterminateProgressDialog("Hello, ${name.text}! Please wait...")
                        progress.show()
                        handler.postDelayed(3000) { progress.dismiss() }
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }

                /**
                 * Button Go to Second Activity:
                 * */
                button("Go to Second Activity"){
                    backgroundColor = ContextCompat.getColor(context, colorAccent)
                    textColor = Color.WHITE
                    onClick {
                        startActivity<SecondActivity>("name" to "${name.text}")
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }
            }
        }

    }
}
