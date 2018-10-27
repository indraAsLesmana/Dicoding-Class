package com.tutor93.menampilkanarray.model.response

import android.os.Parcel
import android.os.Parcelable
import com.tutor93.menampilkanarray.model.League
import com.google.gson.annotations.SerializedName

class LeagueResponse() : Parcelable {
    @SerializedName("leagues")
    var leagues: List<League>? = null

    constructor(parcel: Parcel) : this() {
        leagues = parcel.createTypedArrayList(League)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(leagues)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LeagueResponse> {
        override fun createFromParcel(parcel: Parcel): LeagueResponse {
            return LeagueResponse(parcel)
        }

        override fun newArray(size: Int): Array<LeagueResponse?> {
            return arrayOfNulls(size)
        }
    }
}