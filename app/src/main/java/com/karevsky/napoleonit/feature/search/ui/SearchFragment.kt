package com.karevsky.napoleonit.feature.search.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.feature.search.presenter.GENRES
import com.karevsky.napoleonit.feature.search.presenter.SearchPresenter
import com.karevsky.napoleonit.feature.search.presenter.SearchView
import kotlinx.android.synthetic.main.fragment_search.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SearchFragment : MvpAppCompatFragment(R.layout.fragment_search), SearchView {

    private val presenter: SearchPresenter by moxyPresenter {
        SearchPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.genres_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genreSpinner.adapter = adapter
        }
    }

    private fun initListeners() {
        btnSearch.setOnClickListener {
            presenter.validate(
                etYearFrom.text.toString(),
                etYearTo.text.toString()
            )
        }

        genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                presenter.setGenre(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    override fun showYearError() {
        Toast.makeText(requireContext(), "Неверно введен год.", Toast.LENGTH_SHORT).show()
    }

    override fun showGenre(selectedGenre: GENRES) {
        Toast.makeText(requireContext(), "Жанр: $selectedGenre", Toast.LENGTH_SHORT).show()

    }
}