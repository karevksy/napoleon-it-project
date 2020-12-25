package com.karevsky.napoleonit.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AlbumDetails(val artist: String, val title: String, val duration: Int) : Parcelable
