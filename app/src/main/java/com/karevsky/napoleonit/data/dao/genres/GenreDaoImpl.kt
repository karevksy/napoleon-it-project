package com.karevsky.napoleonit.data.dao.genres

import android.content.SharedPreferences
import com.karevsky.napoleonit.data.dao.favorite.FavoriteDao
import com.karevsky.napoleonit.domain.Genre
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GenresDaoImpl(
    private val sharedPreferences: SharedPreferences
) : GenreDao {

    private var genres: List<Genre>
        get() = sharedPreferences.getString(GENRE_DAO_KEY, null)?.let {
            try {
                Json.decodeFromString(it)
            } catch (t: Throwable) {
                emptyList()
            }
        } ?: emptyList()
        set(value) {
            sharedPreferences.edit().putString(
                GENRE_DAO_KEY,
                Json.encodeToString(value)
            ).apply()
        }

    override fun add(genre: Genre) {
        genres = genres + genre
    }

    override fun remove(genre: Genre) {
        genres = genres.filter { it != genre }
    }

    override fun getAll(): List<Genre> = genres

    companion object {
        private const val GENRE_DAO_KEY = "GENRE_DAO_KEY"
    }
}
