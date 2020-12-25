package com.karevsky.napoleonit.feature.favorites.presenter

import android.content.Context
import android.util.Log
import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.data.FavoriteDao
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
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
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDetail(album: Album)

}
