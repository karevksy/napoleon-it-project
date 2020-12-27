package com.karevsky.napoleonit.data.dao.genres

import com.karevsky.napoleonit.domain.Genre

interface GenreDao {

    /**
     * Добавляет [genre]
     */
    fun add(genre: Genre)

    /**
     * Убирает [genre]
     */
    fun remove(genre: Genre)

    /**
     * @return Список всех жанров
     */
    fun getAll() : List<Genre>
}