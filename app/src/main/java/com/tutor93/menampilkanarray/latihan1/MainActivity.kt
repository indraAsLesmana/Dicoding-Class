package com.tutor93.menampilkanarray.latihan1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.tutor93.menampilkanarray.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var items: MutableList<item> = mutableListOf()
    var message: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()

        club_list.layoutManager = LinearLayoutManager(this)
        club_list.adapter = RecyclerviewAdapter(this, items) {
            message?.cancel()
            message = Toast.makeText(this, it.name, Toast.LENGTH_SHORT)
            message?.show()
        }
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        items.clear()
        for (i in name.indices) {
            items.add(
                item(
                    name[i],
                    image.getResourceId(i, 0)
                )
            )
        }
        //Recycle the typed array, baru tau sy ada yg seperti ini. thanks dicoding!
        image.recycle()
    }
}
