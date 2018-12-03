package com.tutor93.menampilkanarray.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tutor93.menampilkanarray.model.Event


data class MatchesResponse (@SerializedName("event") @Expose var events: List<Event>? = null)