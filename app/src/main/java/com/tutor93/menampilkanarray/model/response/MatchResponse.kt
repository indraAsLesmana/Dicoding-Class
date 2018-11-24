package com.tutor93.menampilkanarray.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tutor93.menampilkanarray.model.Event


class MatchResponse {
    @SerializedName("events")
    @Expose
    var events: List<Event>? = null

    @SerializedName("event")
    @Expose
    var event: List<Event>? = null
}