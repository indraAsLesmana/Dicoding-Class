package com.tutor93.menampilkanarray.submission1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tutor93.menampilkanarray.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SubOneActivity : AppCompatActivity() {
    private var items: MutableList<itemDetail> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subone)
        supportActionBar?.title = "Submission 1"

        initData()
        //SubmissionOneUI().setContentView(this)
    }

    /**
     * issue import androidx with anko Recyclerview
     * @link 'https://github.com/Kotlin/anko/issues/624'
     * */
    class SubmissionOneUI: AnkoComponent<SubOneActivity> {
        override fun createView(ui: AnkoContext<SubOneActivity>) = with(ui) {
                verticalLayout {
                    lparams(matchParent, matchParent)
                    val rc = recyclerView {

                    }
                }
        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.obtainTypedArray(R.array.club_detail)
        items.clear()
        for (i in name.indices) {
            items.add(
                itemDetail(
                    name[i],
                    image.getResourceId(i, 0),
                    desc.getString(i)
                )
            )
        }

        //Recycle the typed array, baru tau sy ada yg seperti ini. thanks dicoding!
        image.recycle()
        desc.recycle()
    }
}