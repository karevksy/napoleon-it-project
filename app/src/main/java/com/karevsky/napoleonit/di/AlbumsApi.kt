package com.karevsky.napoleonit.di

import com.karevsky.napoleonit.data.entities.albumById.AlbumByIdResponse
import com.karevsky.napoleonit.data.entities.genre.GenresResponse
import com.karevsky.napoleonit.data.entities.top.TopAlbumsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumsApi {

    @GET("chart/{genre}/albums")
    suspend fun getTopAlbums(
        @Path("genre") genre: Int,
        @Query("index") index: Int = 0,
        @Query("limit") limit: Int = 50
    ): TopAlbumsResponse

    @GET("album/{albumId}/tracks")
    suspend fun getAlbumById(
        @Path("albumId") albumId: Int
    ): AlbumByIdResponse

    @GET("genre")
    suspend fun getGenres(): GenresResponse
}
