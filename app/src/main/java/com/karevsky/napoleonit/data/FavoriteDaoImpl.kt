package com.karevsky.napoleonit.data

import android.content.SharedPreferences
import com.karevsky.napoleonit.Album
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FavoriteDaoImpl(
    private val sharedPreferences: SharedPreferences
) : FavoriteDao {

    private var albums: List<Album>
        get() = sharedPreferences.getString(FAVORITES_DAO_KEY, null)?.let {
            try {
                Json.decodeFromString(it)
            } catch (t: Throwable) {
                emptyList()
            }
        } ?: emptyList()
        set(value) {
            sharedPreferences.edit().putString(
                FAVORITES_DAO_KEY,
                Json.encodeToString(value)
            ).apply()
        }

    override fun add(album: Album) {
        albums = albums + album
    }

    override fun remove(album: Album) {
        albums = albums.filter { it != album }
    }

    override fun getAll(): List<Album> = albums

    override fun isInFavorites(album: Album): Boolean = albums.contains(album)

    companion object {
        private const val FAVORITES_DAO_KEY = "FAVORITES_DAO_KEY"
    }
}
