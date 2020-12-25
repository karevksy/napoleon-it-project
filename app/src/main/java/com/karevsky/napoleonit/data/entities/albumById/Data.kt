package com.karevsky.napoleonit.data.entities.albumById


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("artist")
    val artist: Artist?,
    @SerialName("disk_number")
    val diskNumber: Int?,
    @SerialName("duration")
    val duration: Int?,
    @SerialName("explicit_content_cover")
    val explicitContentCover: Int?,
    @SerialName("explicit_content_lyrics")
    val explicitContentLyrics: Int?,
    @SerialName("explicit_lyrics")
    val explicitLyrics: Boolean?,
    @SerialName("id")
    val id: Int?,
    @SerialName("isrc")
    val isrc: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("md5_image")
    val md5Image: String?,
    @SerialName("preview")
    val preview: String?,
    @SerialName("rank")
    val rank: Int?,
    @SerialName("readable")
    val readable: Boolean?,
    @SerialName("title")
    val title: String?,
    @SerialName("title_short")
    val titleShort: String?,
    @SerialName("title_version")
    val titleVersion: String?,
    @SerialName("track_position")
    val trackPosition: Int?,
    @SerialName("type")
    val type: String?
)