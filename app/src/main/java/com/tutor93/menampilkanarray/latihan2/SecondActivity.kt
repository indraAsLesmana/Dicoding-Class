package com.tutor93.menampilkanarray.latihan2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.submission1.ItemDetail
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*

class SecondActivity : AppCompatActivity() {
    private var name: String = ""
    private lateinit var nameTextView: TextView
    private lateinit var iconLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Submission 1"

        linearLayout{
            orientation = LinearLayout.VERTICAL
            lparams(matchParent, matchParent)
            padding = dip(16)

            iconLogo = imageView {
                gone()
            }.lparams{
                gravity = Gravity.CENTER + Gravity.TOP
                width = dip(100)
                height = dip(100)
            }

            nameTextView = textView().lparams{
                topMargin = dip(16)
            }
        }

        /**
         * latihan array
         * */
        intent.getStringArrayExtra("name").let {
            nameTextView.text = name
        }

        /**
         * Submission 1
         * */
        intent.getParcelableExtra<ItemDetail>("detail").let {
            iconLogo.visible()
            Glide.with(this).load(it?.image).into(iconLogo)
            nameTextView.text = it?.desc
        }
    }
}
