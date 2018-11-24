package com.tutor93.menampilkanarray.model.response

import android.os.Parcel
import android.os.Parcelable
import com.tutor93.menampilkanarray.model.Team

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
data class TeamResponse(
    val teams: List<Team>?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Team)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(teams)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamResponse> {
        override fun createFromParcel(parcel: Parcel): TeamResponse {
            return TeamResponse(parcel)
        }

        override fun newArray(size: Int): Array<TeamResponse?> {
            return arrayOfNulls(size)
        }
    }

}