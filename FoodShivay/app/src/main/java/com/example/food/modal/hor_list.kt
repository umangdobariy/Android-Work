package com.example.food.modal

import android.os.Parcel
import android.os.Parcelable

data class hor_list(
    var id:Int,
    var Img:Int
)/*:Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt().toInt(),
        parcel.readInt().toInt()
    ) {
    }

    override fun describeContents(): Int {
       return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(id)
        parcel.writeInt(Img)

    }

    companion object CREATOR : Parcelable.Creator<hor_list> {
        override fun createFromParcel(parcel: Parcel): hor_list {
            return hor_list(parcel)
        }

        override fun newArray(size: Int): Array<hor_list?> {
            return arrayOfNulls(size)
        }
    }

}
*/