package com.karevsky.napoleonit.data.entities.albumById


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumByIdResponse(
    @SerialName("data")
    val tracks: List<Data>?,
    @SerialName("total")
    val total: Int?
)