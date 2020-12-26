package com.karevsky.napoleonit.feature.topAlbums.presenter

import com.karevsky.napoleonit.data.dao.favorite.FavoriteDao
import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.domain.GetTopAlbumsUseCase
import com.karevsky.napoleonit.domain.GetTopAlbumsUseCaseFactory
import com.karevsky.napoleonit.utils.launchWithErrorHandler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

class TopAlbumsPresenterFactory @Inject constructor(
    private val getTopAlbumsUseCaseFactory: GetTopAlbumsUseCaseFactory,
    private val favoriteDao: FavoriteDao
) {
    fun create(genreId: Int = 0) =
        TopAlbumsPresenter(getTopAlbumsUseCaseFactory.create(genreId), favoriteDao)
}

class TopAlbumsPresenter(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val favoriteDao: FavoriteDao,
) : MvpPresenter<TopAlbumsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(isShow = true)
        presenterScope.launchWithErrorHandler(block = {
            val albums = getTopAlbumsUseCase()
            viewState.setAlbums(albums)
            viewState.showLoading(isShow = false)
        }, onError = {
            viewState.showLoading(false)
            viewState.showError()
        })
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
     * Открывает фрагмент с описанием [album]
     */
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openAlbumDetail(album: Album)

    /**
     * Отображает ошибку, если не удалось получить данные с API
     */
    @StateStrategyType(SkipStrategy::class)
    fun showError()

    /**
     * Отображает загрузку списка, в зависимости от [isShow]
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(isShow: Boolean)

}
