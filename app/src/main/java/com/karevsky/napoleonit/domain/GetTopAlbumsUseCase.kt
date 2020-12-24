package com.karevsky.napoleonit.domain

import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.di.AlbumsApi
import com.karevsky.napoleonit.di.albumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTopAlbumsUseCase(private val albumsApi: AlbumsApi) {

    suspend operator fun invoke(): List<Album> = withContext(Dispatchers.IO) {
        albumApi.getTopAlbums().run {
            albums?.mapNotNull { album ->
                Album(
                    id = album.id ?: return@mapNotNull  null,
                    name = album.title ?: return@mapNotNull null,
                    band = album.artist?.name ?: return@mapNotNull null,
                    imgSource = album.coverMedium ?: return@mapNotNull null
                )
            } ?: emptyList()
        }
    }
}