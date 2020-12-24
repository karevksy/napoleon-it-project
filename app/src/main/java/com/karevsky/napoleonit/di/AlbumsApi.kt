package com.karevsky.napoleonit.di

import com.karevsky.napoleonit.data.entities.top.TopAlbumsResponse
import com.karevsky.napoleonit.data.entities.albumsById.AlbumByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  AlbumsApi {

    @GET("chart/0/albums")
    suspend fun getTopAlbums(
        @Query("index") index: Int = 0,
        @Query("limit") limit: Int = 50
    ) : TopAlbumsResponse

    @GET("album/{albumId}/tracks")
    suspend fun getAlbumById(
        @Path("albumId") albumId: Int
    ) : AlbumByIdResponse
}
