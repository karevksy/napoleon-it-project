package com.karevsky.napoleonit.feature.topAlbums.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import com.karevsky.napoleonit.di.albumApi
import com.karevsky.napoleonit.domain.GetTopAlbumsUseCase
import com.karevsky.napoleonit.feature.detail.ui.AlbumDetailsFragment
import com.karevsky.napoleonit.feature.topAlbums.presenter.TopAlbumsPresenter
import com.karevsky.napoleonit.feature.topAlbums.presenter.TopAlbumsView
import kotlinx.android.synthetic.main.fragment_top_albums.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TopAlbumsFragment : MvpAppCompatFragment(R.layout.fragment_top_albums), TopAlbumsView {

    private val presenter: TopAlbumsPresenter by moxyPresenter {
        TopAlbumsPresenter(
            getTopAlbumsUseCase = GetTopAlbumsUseCase(albumApi),
            favoriteDao = FavoriteDaoImpl(
                requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            )
        )
    }
    private var albumsAdapter: TopAlbumsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(rvTopAlbums) {
            layoutManager = LinearLayoutManager(context)
            adapter = TopAlbumsAdapter(
                onAlbumClick = { album ->
                    presenter.onAlbumClick(album)
                },
                onSetFavClick = { album ->
                    presenter.onSetFavClick(album)
                },
                favoriteDao = FavoriteDaoImpl(
                    requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
                )
            ).also {
                albumsAdapter = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        albumsAdapter = null
    }

    override fun setAlbums(albums: List<Album>) {
        albumsAdapter?.submitList(albums)
    }

    override fun openAlbumDetail(album: Album) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, AlbumDetailsFragment.newInstance(album))
            .addToBackStack("Details")
            .commit()
    }

    override fun showError() {
        Toast.makeText(
            requireContext(),
            "Error with getting request from server!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showLoading(isShow: Boolean) {
        topAlbumsProgress.isVisible = isShow
    }

}

