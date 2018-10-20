package com.tutor93.menampilkanarray.latihan1

import android.os.Parcel
import android.os.Parcelable

data class item(val name: String?, val image: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<item> {
        override fun createFromParcel(parcel: Parcel): item {
            return item(parcel)
        }

        override fun newArray(size: Int): Array<item?> {
            return arrayOfNulls(size)
        }
    }
}