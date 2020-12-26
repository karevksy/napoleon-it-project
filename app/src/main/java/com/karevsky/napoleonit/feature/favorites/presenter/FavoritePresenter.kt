package com.karevsky.napoleonit.feature.favorites.presenter

import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.data.dao.favorite.FavoriteDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

class FavoritePresenter @Inject constructor(
    private val favoriteDao: FavoriteDao
) : MvpPresenter<FavoriteView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setAlbums()
    }

    fun getDao(): FavoriteDao = favoriteDao

    fun onAlbumClick(album: Album) {
        viewState.openDetail(album)
    }

    fun showFavoriteTextView() {
        viewState.showFavoriteTextView(favoriteDao.getAll().isNotEmpty())
    }

    fun onRemoveClick(album: Album) {
        favoriteDao.remove(album)
        viewState.setAlbums()
    }
}

interface FavoriteView : MvpView {

    /**
     * Устанавливает альбомы, находящиеся в Shared Preferences
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAlbums()

    /**
     * Открывает фрагмент с описанием [album]
     */
    @StateStrategyType(SkipStrategy::class)
    fun openDetail(album: Album)

    /**
     * Отображает ошибку, если не удалось получить данные с API
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFavoriteTextView(isFavoritesNull: Boolean)

}
