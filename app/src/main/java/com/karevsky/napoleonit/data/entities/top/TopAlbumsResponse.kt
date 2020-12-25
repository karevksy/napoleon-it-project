package com.karevsky.napoleonit.data.entities.top


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopAlbumsResponse(
    @SerialName("data")
    val albums: List<Data>?,
    @SerialName("total")
    val total: Int?
)