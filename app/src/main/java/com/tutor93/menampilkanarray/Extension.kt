package com.tutor93.menampilkanarray

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.annotation.AttrRes
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.tutor93.menampilkanarray.data.MyDatabaseOpenHelper
import com.tutor93.menampilkanarray.model.Event
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

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

fun Context.showMessage(message: String, showLongMessage: Boolean = false) {
    Toast.makeText(this, message, if (showLongMessage) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun String.withValidLigaId(): String {
    return when(this){
        "English League Championship"   ->{ "4329" }
        "German Bundesliga"             ->{ "4331" }
        "Italian Serie A"               ->{ "4332" }
        "French Ligue 1"                ->{ "4334" }
        "Spanish La Liga"               ->{ "4335" }
        else                            ->{ "4328" }
    }
}

fun Context.saveEventDate(data: Event) {
    val calIntent = Intent(Intent.ACTION_INSERT)
    calIntent.type = "vnd.android.cursor.item/event"
    calIntent.putExtra(CalendarContract.Events.TITLE, data.strEvent)
    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, data.strDescriptionEN)
    val cal = Calendar.getInstance()
    cal.time = data.dateEvent
    calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
    calIntent.putExtra(
        CalendarContract.EXTRA_EVENT_BEGIN_TIME,
        cal.timeInMillis
    )
    calIntent.putExtra(
        CalendarContract.EXTRA_EVENT_END_TIME,
        cal.timeInMillis
    )
    startActivity(calIntent)
}
