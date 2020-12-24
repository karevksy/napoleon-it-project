package com.karevsky.napoleonit.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Album(val id : Int, val name: String, val band: String, val imgSource: String, val imgSourceBig: String) : Parcelable