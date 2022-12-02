package com.example.xampplocal.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class User (
        var id:String,
        var name:String,
        var email:String,
        @SerializedName("mobile")
        var contact:String,
        var active_flag:Int
        ) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(id)
                parcel.writeString(name)
                parcel.writeString(email)
                parcel.writeString(contact)
                parcel.writeInt(active_flag)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<User> {
                override fun createFromParcel(parcel: Parcel): User {
                        return User(parcel)
                }

                override fun newArray(size: Int): Array<User?> {
                        return arrayOfNulls(size)
                }
        }
}