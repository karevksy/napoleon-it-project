package com.karevsky.napoleonit.domain

import com.karevsky.napoleonit.di.AlbumsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopAlbumsUseCaseFactory @Inject constructor(
    private val albumsApi: AlbumsApi
) {
    fun create(genreId: Int) = GetTopAlbumsUseCase(genreId = genreId, albumsApi = albumsApi)
}

class GetTopAlbumsUseCase(
    private val albumsApi: AlbumsApi,
    private val genreId: Int = 0
) {

    suspend operator fun invoke(): List<Album> = withContext(Dispatchers.IO) {
        albumsApi.getTopAlbums(genre = genreId).run {
            albums?.mapNotNull { album ->
                Album(
                    id = album.id ?: return@mapNotNull null,
                    name = album.title ?: return@mapNotNull null,
                    band = album.artist?.name ?: return@mapNotNull null,
                    imgSource = album.coverMedium ?: return@mapNotNull null,
                    imgSourceBig = album.coverXl ?: return@mapNotNull null
                )
            } ?: emptyList()
        }
    }
}