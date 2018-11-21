package com.tutor93.menampilkanarray.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tutor93.menampilkanarray.model.Player


class SearchPlayerResponse() : Parcelable {
    @SerializedName("player")
    @Expose
    var player: List<Player>? = null

    constructor(parcel: Parcel) : this() {
        player = parcel.createTypedArrayList(Player)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(player)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchPlayerResponse> {
        override fun createFromParcel(parcel: Parcel): SearchPlayerResponse {
            return SearchPlayerResponse(parcel)
        }

        override fun newArray(size: Int): Array<SearchPlayerResponse?> {
            return arrayOfNulls(size)
        }
    }

}