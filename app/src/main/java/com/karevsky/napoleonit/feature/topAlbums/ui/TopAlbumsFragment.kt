package com.karevsky.napoleonit.feature.topAlbums.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.feature.detail.ui.AlbumDetailsFragment
import com.karevsky.napoleonit.feature.topAlbums.presenter.TopAlbumsPresenter
import com.karevsky.napoleonit.feature.topAlbums.presenter.TopAlbumsView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_albums.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class TopAlbumsFragment : MvpAppCompatFragment(R.layout.fragment_top_albums), TopAlbumsView {

    @Inject
    lateinit var topAlbumsPresenter: TopAlbumsPresenter

    @Inject
    lateinit var topAlbumsAdapterFactory: TopAlbumsAdapterFactory

    private val presenter: TopAlbumsPresenter by moxyPresenter { topAlbumsPresenter }

    private var albumsAdapter: TopAlbumsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(rvTopAlbums) {
            layoutManager = LinearLayoutManager(context)
            adapter = topAlbumsAdapterFactory.create(
                onAlbumClick = { album ->
                    presenter.onAlbumClick(album)
                },
                onSetFavClick = { album ->
                    presenter.onSetFavClick(album)
                }
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
        parentFragmentManager.beginTransaction()
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

