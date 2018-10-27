package com.tutor93.menampilkanarray.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class League() : Parcelable {
    @SerializedName("idLeague")
    var idLeague: String? = null
    @SerializedName("strLeague")
    var strLeague: String? = null
    @SerializedName("strSport")
    var strSport: String? = null
    @SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String? = null

    constructor(parcel: Parcel) : this() {
        idLeague = parcel.readString()
        strLeague = parcel.readString()
        strSport = parcel.readString()
        strLeagueAlternate = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idLeague)
        parcel.writeString(strLeague)
        parcel.writeString(strSport)
        parcel.writeString(strLeagueAlternate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<League> {
        override fun createFromParcel(parcel: Parcel): League {
            return League(parcel)
        }

        override fun newArray(size: Int): Array<League?> {
            return arrayOfNulls(size)
        }
    }
}
