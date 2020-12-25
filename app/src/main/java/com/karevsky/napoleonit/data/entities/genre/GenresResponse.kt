package com.karevsky.napoleonit.data.entities.genre


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    @SerialName("data")
    val genres: List<Data>?
)