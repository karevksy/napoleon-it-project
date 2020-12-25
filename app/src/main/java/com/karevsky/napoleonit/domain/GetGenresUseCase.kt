package com.karevsky.napoleonit.domain

import com.karevsky.napoleonit.di.AlbumsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val albumsApi: AlbumsApi) {

    suspend operator fun invoke() : List<Genre> = withContext(Dispatchers.IO){
        albumsApi.getGenres().run {
            genres?.mapNotNull { genre ->
                Genre(
                    name = genre.name ?: return@mapNotNull null,
                    srcImage = genre.pictureMedium ?: return@mapNotNull null,
                    id = genre.id ?: return@mapNotNull null
                )
            }
        } ?: emptyList()
    }
}