package com.karevsky.napoleonit.domain

import kotlinx.serialization.Serializable

@Serializable
data class Genre(val id: Int, val name: String, val srcImage: String)
