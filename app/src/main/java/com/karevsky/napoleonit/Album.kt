package com.karevsky.napoleonit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Album(val name: String, val band: String, val tracks: Int) : Parcelable