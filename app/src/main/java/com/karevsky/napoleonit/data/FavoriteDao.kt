package com.karevsky.napoleonit.data

import com.karevsky.napoleonit.Album

interface FavoriteDao {

    /**
     * Добавляет [album] в избранное
     */
    fun add(album: Album)

    /**
     * Убирает [album] из избранного
     */
    fun remove(album: Album)

    /**
     * @return Список всех альбомов
     */
    fun getAll() : List<Album>

    /**
     * @return true если [album] в избранном, иначе false
     */
    fun isInFavorites(album: Album) : Boolean
}