package com.karevsky.napoleonit.feature.search

import java.lang.NumberFormatException

class SearchPresenter(private val view : SearchView){

    var yearBeg : Int? = 1950
    var yearEnd : Int? = 2020
    var selectedGenre :GENRES = GENRES.ALL

    fun validate(yearFrom: String, yearTo: String) {
        when{
            !yearIsCorrect(yearFrom, yearTo) -> view.showYearError()
        }
    }

    fun setGenre(genre: GENRES) {
            this.selectedGenre = genre
            view.showGenre(selectedGenre)
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