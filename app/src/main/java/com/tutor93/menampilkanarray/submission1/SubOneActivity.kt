package com.tutor93.menampilkanarray.submission1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.detailview.DetailActivity
import com.tutor93.menampilkanarray.model.ItemDetail
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SubOneActivity : AppCompatActivity() {
    private var items: MutableList<ItemDetail> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.label_submission_1)

        initData()
        SubmissionOneUI().setContentView(this)
    }

    /**
     * issue conflict androidx with anko Recyclerview
     * @link 'https://github.com/Kotlin/anko/issues/624'
     * solved: migrate andoridx to support.library
     * */
    inner class SubmissionOneUI: AnkoComponent<SubOneActivity> {
        override fun createView(ui: AnkoContext<SubOneActivity>) = with(ui) {
                verticalLayout {
                    lparams(matchParent, matchParent)
                    orientation = LinearLayout.VERTICAL
                    recyclerView {
                        lparams(matchParent, matchParent)
                        layoutManager = LinearLayoutManager(context)
                        adapter = SubOneAdapter(context, items){
                            startActivity<DetailActivity>("detail" to it)
                        }
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
                ItemDetail(
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