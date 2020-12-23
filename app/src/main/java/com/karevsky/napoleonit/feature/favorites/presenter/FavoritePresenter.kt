package com.karevsky.napoleonit.feature.favorites.presenter

import android.content.Context
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.data.FavoriteDao
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

class FavoritePresenter(
    private val favoriteDao: FavoriteDao
) : MvpPresenter<FavoriteView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setAlbums()
    }

    fun getDao(context: Context): FavoriteDao =
        FavoriteDaoImpl(context.getSharedPreferences("data", Context.MODE_PRIVATE))

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
