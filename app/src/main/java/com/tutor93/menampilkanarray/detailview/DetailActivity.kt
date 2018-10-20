package com.tutor93.menampilkanarray.detailview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.model.ItemDetail
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.Bold
import org.jetbrains.anko.Italic

class DetailActivity : AppCompatActivity() {
    private var name: String = ""
    private lateinit var nameTextView: TextView
    private lateinit var iconLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            supportActionBar?.title = getString(R.string.label_submission_1)

            iconLogo.visible()
            Glide.with(this).load(it?.image).into(iconLogo)
            nameTextView.text = buildSpanned {
                append(it?.name.toString(), Bold, Italic)
                append(" " + it?.desc)
            }
        }
    }
}
