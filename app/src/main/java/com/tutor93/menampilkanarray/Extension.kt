package com.tutor93.menampilkanarray

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.AttrRes
import android.util.TypedValue
import android.view.View
import java.io.IOException
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

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

@SuppressLint("SimpleDateFormat")
fun Date.formated(): String {
    val sdf = SimpleDateFormat("E, dd MMM yyyy")
    return sdf.format(this)
}

@SuppressLint("SimpleDateFormat")
fun String.toStringDateFormat(fromFormat: String, toFormat: String): String{
    val form = SimpleDateFormat(fromFormat)
    val to = SimpleDateFormat(toFormat)
    return to.format(form.parse(this))
}

val Context.selectableItemBackgroundResource: Int get() {
    return getResourceIdAttribute(R.attr.selectableItemBackground)
}

fun Context.getResourceIdAttribute(@AttrRes attribute: Int) : Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attribute, typedValue, true)
    return typedValue.resourceId
}

