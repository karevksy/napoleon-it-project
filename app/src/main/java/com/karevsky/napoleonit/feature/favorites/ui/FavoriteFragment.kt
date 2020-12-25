package com.karevsky.napoleonit.feature.favorites.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.feature.detail.ui.AlbumDetailsFragment
import com.karevsky.napoleonit.feature.favorites.presenter.FavoritePresenter
import com.karevsky.napoleonit.feature.favorites.presenter.FavoriteView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : MvpAppCompatFragment(R.layout.fragment_favorite), FavoriteView {

    @Inject
    lateinit var favoritePresenter: FavoritePresenter

    private val presenter: FavoritePresenter by moxyPresenter { favoritePresenter }
    private var albumsAdapter: FavoriteAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvFavoriteAlbums) {
            layoutManager = LinearLayoutManager(context)
            adapter = FavoriteAdapter(
                onAlbumClick = { album ->
                    presenter.onAlbumClick(album)
                }
            ) { album ->
                presenter.onRemoveClick(album)
            }.also {
                albumsAdapter = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        albumsAdapter = null
    }

    override fun setAlbums() {
        albumsAdapter?.submitList(presenter.getDao().getAll())
    }

    override fun openDetail(album: Album) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToAlbumDetailsFragment(album)
        findNavController().navigate(action)
    }
}