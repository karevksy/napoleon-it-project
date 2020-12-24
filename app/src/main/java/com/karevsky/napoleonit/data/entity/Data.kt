package com.karevsky.napoleonit.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("artist")
    val artist: Artist?,
    @SerialName("cover")
    val cover: String?,
    @SerialName("cover_big")
    val coverBig: String?,
    @SerialName("cover_medium")
    val coverMedium: String?,
    @SerialName("cover_small")
    val coverSmall: String?,
    @SerialName("cover_xl")
    val coverXl: String?,
    @SerialName("explicit_lyrics")
    val explicitLyrics: Boolean?,
    @SerialName("id")
    val id: Int?,
    @SerialName("link")
    val link: String?,
    @SerialName("md5_image")
    val md5Image: String?,
    @SerialName("position")
    val position: Int?,
    @SerialName("record_type")
    val recordType: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("tracklist")
    val tracklist: String?,
    @SerialName("type")
    val type: String?
)