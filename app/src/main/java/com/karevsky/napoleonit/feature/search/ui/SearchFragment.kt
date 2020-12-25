package com.karevsky.napoleonit.feature.search.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.domain.Genre
import com.karevsky.napoleonit.feature.search.presenter.SearchPresenter
import com.karevsky.napoleonit.feature.search.presenter.SearchView
import com.karevsky.napoleonit.feature.topAlbums.ui.TopAlbumsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : MvpAppCompatFragment(R.layout.fragment_search), SearchView {

    @Inject
    lateinit var searchPresenter: SearchPresenter

    private var searchGenresAdapter: SearchGenresAdapter? = null


    private val presenter: SearchPresenter by moxyPresenter { searchPresenter }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(rvGenres) {
            layoutManager =
                GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = SearchGenresAdapter(
                onItemClick = { genre ->
                    presenter.onItemClick(genre)
                }
            ).also {
                searchGenresAdapter = it
            }
        }
    }

    override fun setGenres(genres: List<Genre>) {
        searchGenresAdapter?.submitList(genres)
    }

    override fun showError() {
        Toast.makeText(
            context,
            "Error with getting request from API",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun openGenreList(genreId: Int, genreTitle: String) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, TopAlbumsFragment.newInstance(genreId, genreTitle))
            .commit()
    }
}