package com.tutor93.menampilkanarray

import android.content.Context
import android.view.View
import java.io.IOException
import java.nio.charset.Charset

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Context.jsonString(fileName: String): String? {
    try {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.defaultCharset())

    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}
