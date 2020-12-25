package com.karevsky.napoleonit.feature.search.presenter

import android.util.Log
import com.karevsky.napoleonit.domain.Genre
import com.karevsky.napoleonit.domain.GetGenresUseCase
import com.karevsky.napoleonit.utils.launchWithErrorHandler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
) : MvpPresenter<SearchView>() {
    private var genres: List<Genre> = listOf()
    private var tempGenres: MutableList<Genre> = mutableListOf()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoad(isShow = true)
        presenterScope.launchWithErrorHandler(block = {
            genres = getGenresUseCase()
            viewState.setGenres(genres)
            viewState.showLoad(false)
        }, onError = {
            viewState.showError()
        })

    }

    fun onItemClick(genre: Genre) {
        viewState.openGenreList(genre.id, genre.name)
    }

}


interface SearchView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setGenres(genres: List<Genre>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openGenreList(genreId: Int, genreTitle: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoad(isShow: Boolean)


}