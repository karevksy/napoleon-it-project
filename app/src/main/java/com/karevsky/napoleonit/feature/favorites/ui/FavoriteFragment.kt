package com.karevsky.napoleonit.feature.favorites.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import com.karevsky.napoleonit.feature.detail.ui.AlbumDetailsFragment
import com.karevsky.napoleonit.feature.favorites.presenter.FavoritePresenter
import com.karevsky.napoleonit.feature.favorites.presenter.FavoriteView
import kotlinx.android.synthetic.main.fragment_favorite.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FavoriteFragment : MvpAppCompatFragment(R.layout.fragment_favorite), FavoriteView {

    private val presenter: FavoritePresenter by moxyPresenter {
        FavoritePresenter(
            favoriteDao = FavoriteDaoImpl(
                requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            )
        )
    }
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
        albumsAdapter?.submitList(presenter.getDao(requireContext()).getAll())
    }

    override fun openDetail(album: Album) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, AlbumDetailsFragment.newInstance(album))
            .commit()
    }
}