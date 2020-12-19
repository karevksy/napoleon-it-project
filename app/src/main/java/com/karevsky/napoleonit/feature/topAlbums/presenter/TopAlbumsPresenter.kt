package com.karevsky.napoleonit.feature.topAlbums.presenter

import com.karevsky.napoleonit.Album
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

class TopAlbumsPresenter : MvpPresenter<TopAlbumsView>() {
    private val albums: List<Album> = listOf(
        Album("OOFIE", "Wiki", 8),
        Album("Humanz", "Gorillaz", 12),
        Album("99.9%", "KAYTRANADA", 9),
        Album("Fires In Heaven", "Salem", 14),
        Album("Chronovision", "Oberhofer", 18),
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

}

interface TopAlbumsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAlbums(albums: List<Album>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openAlbumDetail(album: Album)


}
