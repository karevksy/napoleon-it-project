package com.karevsky.napoleonit.feature.topAlbums.presenter

import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.data.FavoriteDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

class TopAlbumsPresenter(
    private val favoriteDao: FavoriteDao
) : MvpPresenter<TopAlbumsView>() {
    private val albums: List<Album> = listOf(
        Album("OOFIE", "Wiki", 8),
        Album("Humanz", "Gorillaz", 12),
        Album("99.9%", "KAYTRANADA", 9),
        Album("Fires In Heaven", "Salem", 14),
        Album("Chronovision", "Oberhofer", 18)
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setAlbums(albums)
    }

    fun onAlbumClick(album: Album) {
        viewState.openAlbumDetail(album)
    }

    fun onSetFavClick(album: Album) {
        if (!favoriteDao.isInFavorites(album)) {
            favoriteDao.add(album)
        } else {
            favoriteDao.remove(album)
        }
    }
}

interface TopAlbumsView : MvpView {

    /**
     * Устанавливает список [albums]
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAlbums(albums: List<Album>)

    /**
     * Открывает детальное описание [album]
     */
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openAlbumDetail(album: Album)

}
