package com.karevsky.napoleonit.feature.search.presenter

import com.karevsky.napoleonit.data.dao.genres.GenreDao
import com.karevsky.napoleonit.data.dao.genres.GenresDaoImpl
import com.karevsky.napoleonit.domain.Genre
import com.karevsky.napoleonit.domain.GetGenresUseCase
import com.karevsky.napoleonit.utils.launchWithErrorHandler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val genreDao: GenreDao
) : MvpPresenter<SearchView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoad(isShow = true)
        presenterScope.launchWithErrorHandler(block = {
            val genres = getGenresUseCase()
            if(genreDao.getAll().isEmpty()){
                for(genre in genres){
                    genreDao.add(genre)
                }
            }
            viewState.setGenres(genreDao.getAll())
            viewState.showLoad(false)
        }, onError = {
            viewState.showError()
            viewState.showLoad(false)
        })
    }


    fun onItemClick(genre: Genre) {
        viewState.openGenreList(genre.id, genre.name)
    }
}

interface SearchView : MvpView {

    /**
     * Отображает загрузку списка, в зависимости от [isShow]
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoad(isShow: Boolean)

    /**
     * Устанавливает [genres] в SearchGenresAdapter
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setGenres(genres: List<Genre>)

    /**
     * Отображает ошибку, если не удалось получить данные с API
     */
    @StateStrategyType(SkipStrategy::class)
    fun showError()

    /**
     * Открывает список с топом альбомов по [genreId]
     */
    @StateStrategyType(SkipStrategy::class)
    fun openGenreList(genreId: Int, genreTitle: String)


}