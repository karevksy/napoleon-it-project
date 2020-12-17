package com.karevsky.napoleonit.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.FragmentSearchBinding

enum class GENRES {ALL, ROCK, INDIE, HIP_HOP, ALTERNATIVE, POST_HC, ELECTRONIC, TECHNO}

class SearchFragment : Fragment(R.layout.fragment_search), SearchView{

    private lateinit var bind : FragmentSearchBinding
    private val genres = GENRES.values()
    private val presenter = SearchPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        bind = FragmentSearchBinding.inflate(inflater)
        return bind.root
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
            bind.genreSpinner.adapter = adapter
        }
    }

    private fun initListeners() {
        bind.btnSearch.setOnClickListener {
            presenter.validate(
                    bind.etYearFrom.text.toString(),
                    bind.etYearTo.text.toString()
            )
        }

        bind.genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                for (genre in genres){
                    if(genre.ordinal == position) {
                        presenter.setGenre(genre)
                        break
                        }
                    }
                }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    override fun showYearError() {
        Toast.makeText(requireContext(), "Неверно введен год.", Toast.LENGTH_SHORT).show()
    }

    override fun showGenre(selectedGenre: GENRES) {
        Toast.makeText(requireContext(), "Жанр: $selectedGenre", Toast.LENGTH_SHORT).show()

    }
}