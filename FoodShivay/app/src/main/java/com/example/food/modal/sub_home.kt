package com.example.food.modal

import android.os.Parcel
import android.os.Parcelable

data class sub_home(
    var id:Int,
    var img:Int,
    var Tit:String,
    var price:String
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(img)
        parcel.writeString(Tit)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<sub_home> {
        override fun createFromParcel(parcel: Parcel): sub_home {
            return sub_home(parcel)
        }

        override fun newArray(size: Int): Array<sub_home?> {
            return arrayOfNulls(size)
        }
    }

}
