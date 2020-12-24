package com.karevsky.napoleonit.feature.detail.presenter

import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.data.FavoriteDao
import com.karevsky.napoleonit.domain.AlbumDetails
import com.karevsky.napoleonit.domain.GetAlbumByIdUseCase
import com.karevsky.napoleonit.utils.launchWithErrorHandler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

class DetailPresenter(
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase,
    private val album: Album,
    private val favoriteDao: FavoriteDao
) : MvpPresenter<DetailView>() {

    private var isInFavorites : Boolean = favoriteDao.isInFavorites(album)
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoad(isShow = true)
        presenterScope.launchWithErrorHandler( block = {
            val details = getAlbumByIdUseCase()
            viewState.setIsInFavorites(isInFavorites)
            viewState.setDetails(album, details)
            viewState.showLoad(isShow = false)
        })
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

    /**
     * Устанавливает поля [album] фрагменту
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setDetails(album: Album, details: List<AlbumDetails>)

    /**
     * Устанавливает альбом [inFavorites]
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setIsInFavorites(inFavorites: Boolean)

    /**
     * Отображает загрузку в зависимости от [isShow]
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoad(isShow: Boolean)

}
