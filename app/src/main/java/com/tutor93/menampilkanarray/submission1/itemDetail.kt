package com.tutor93.menampilkanarray.submission1

import android.os.Parcel
import android.os.Parcelable

data class ItemDetail(val name: String?, val image: Int?, val desc: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(image)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemDetail> {
        override fun createFromParcel(parcel: Parcel): ItemDetail {
            return ItemDetail(parcel)
        }

        override fun newArray(size: Int): Array<ItemDetail?> {
            return arrayOfNulls(size)
        }
    }

}