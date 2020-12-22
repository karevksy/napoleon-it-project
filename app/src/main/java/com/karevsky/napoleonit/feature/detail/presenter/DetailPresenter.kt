package com.karevsky.napoleonit.feature.detail.presenter

import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.data.FavoriteDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

class DetailPresenter(
    private val album: Album,
    private val favoriteDao: FavoriteDao
) : MvpPresenter<DetailView>() {

    private var isInFavorites : Boolean = favoriteDao.isInFavorites(album)
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setDetails(album)
        viewState.setIsInFavorites(isInFavorites)
    }

    fun onFavClicked(){
        if (isInFavorites) {
            favoriteDao.remove(album)
        } else {
            favoriteDao.add(album)
        }
        isInFavorites = !isInFavorites
        viewState.setIsInFavorites(isInFavorites)
    }
}

interface DetailView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setDetails(album: Album)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setIsInFavorites(inFavorites: Boolean)

}
