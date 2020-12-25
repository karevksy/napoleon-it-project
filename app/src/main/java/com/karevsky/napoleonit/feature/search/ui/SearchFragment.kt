package com.karevsky.napoleonit.feature.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
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

        etGenres.imeOptions = EditorInfo.IME_ACTION_DONE
        etGenres.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("FilteringText", "$newText")
                searchGenresAdapter?.filter?.filter(newText)
                return false
            }

        })
    }

    override fun setGenres(genres: List<Genre>) {
        searchGenresAdapter?.setData(genres)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchGenresAdapter = null
    }

    override fun showError() {
        Toast.makeText(
            context,
            "Error with getting request from API",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun openGenreList(genreId: Int, genreTitle: String) {
        val action = SearchFragmentDirections.actionSearchFragmentToTopAlbumsFragment(genreId)
        findNavController().navigate(action)
    }

    override fun showLoad(isShow: Boolean) {
        genresProgress.isVisible = isShow
    }

}