package com.karevsky.napoleonit.feature.search.presenter

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
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        presenterScope.launchWithErrorHandler(block = {
            val genres = getGenresUseCase()
            viewState.setGenres(genres)
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


}