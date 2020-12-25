package com.karevsky.napoleonit.domain

import com.karevsky.napoleonit.di.AlbumsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAlbumByIdUseCaseFactory @Inject constructor(
    private val albumsApi: AlbumsApi
    ) {
    fun create(albumId: Int) = GetAlbumByIdUseCase(albumId, albumsApi)
}

class GetAlbumByIdUseCase(
    private val albumId: Int,
    private val albumsApi: AlbumsApi
) {

    suspend operator fun invoke(): List<AlbumDetails> = withContext(Dispatchers.IO) {
        albumsApi.getAlbumById(albumId).run {
            tracks?.mapNotNull { track ->
                AlbumDetails(
                    artist = track.artist?.name ?: return@mapNotNull null,
                    title = track.title ?: return@mapNotNull null,
                    duration = track.duration ?: return@mapNotNull null,
                )
            } ?: emptyList()
        }
    }
}