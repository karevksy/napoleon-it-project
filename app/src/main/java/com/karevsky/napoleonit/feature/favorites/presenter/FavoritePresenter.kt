package com.karevsky.napoleonit.feature.favorites.presenter

import android.content.Context
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.data.FavoriteDao
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
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
        viewState.removeAlbum(album)
    }
}

interface FavoriteView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAlbums()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openDetail(album: Album)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeAlbum(album: Album)

}
