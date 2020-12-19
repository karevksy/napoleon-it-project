package com.karevsky.napoleonit.feature.search.presenter

import java.lang.NumberFormatException

enum class GENRES {ALL, ROCK, INDIE, HIP_HOP, ALTERNATIVE, POST_HC, ELECTRONIC, TECHNO}

class SearchPresenter(private val view : SearchView){

    var yearBeg : Int? = 1950
    var yearEnd : Int? = 2020
    private val genres = GENRES.values()
    var selectedGenre : GENRES = GENRES.ALL

    fun validate(yearFrom: String, yearTo: String) {
        when{
            !yearIsCorrect(yearFrom, yearTo) -> view.showYearError()
        }
    }

    fun setGenre(position: Int) {
        for (genre in genres){
            if(genre.ordinal == position) {
                selectedGenre = genre
                break
            }
        }
    }

    private fun yearIsCorrect(yearFrom: String, yearTo: String) : Boolean{
        if (yearFrom.isEmpty() || yearTo.isEmpty()) return false

        return try {
            this.yearBeg = yearFrom.toInt()
            this.yearEnd = yearTo.toInt()
            yearBeg in 1950..2020  && yearEnd in 1950..2020 && yearBeg!! < yearEnd!!
        } catch (e: NumberFormatException){
            false
        }
    }

}

interface SearchView {

    fun showYearError()

    fun showGenre(selectedGenre: GENRES)
}