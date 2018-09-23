
package Presenter

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable


@SuppressLint("ParcelCreator")
data class City(
        val cityName: String,
        val country: String): Parcelable {

    val cityAndCountry: String get() = "$cityName,$country"

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cityName)
        parcel.writeString(country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}