package com.karevsky.napoleonit.feature.search

import java.lang.Exception
import java.lang.NumberFormatException


class SearchPresenter(private val view : SearchView){

    var selectedGenre = GENRES.ALL

    fun validate(yearFrom: String, yearTo: String) {
        when{
            !yearIsCorrect(yearFrom, yearTo) -> view.showYearError()
        }
    }

    fun getGenre(genre: GENRES) {
        this.selectedGenre = genre
    }

    private fun yearIsCorrect(yearFrom: String, yearTo: String) : Boolean{
        if (yearFrom.isEmpty() || yearTo.isEmpty()) return false

        return  try {
            val begYear = yearFrom.toInt()
            val endYear = yearTo.toInt()
            begYear in 1950..2020
            endYear in 1950..2020
            begYear < endYear
        } catch (e: NumberFormatException){
            false
        }
    }
}