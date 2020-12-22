package com.karevsky.napoleonit.feature.topAlbums.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import com.karevsky.napoleonit.feature.detail.ui.AlbumDetailsFragment
import com.karevsky.napoleonit.feature.topAlbums.presenter.TopAlbumsPresenter
import com.karevsky.napoleonit.feature.topAlbums.presenter.TopAlbumsView
import kotlinx.android.synthetic.main.fragment_top_albums.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TopAlbumsFragment : MvpAppCompatFragment(R.layout.fragment_top_albums), TopAlbumsView {

    private val presenter: TopAlbumsPresenter by moxyPresenter {
        TopAlbumsPresenter(
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
        albumsAdapter?.setData(albums)
    }

    override fun openAlbumDetail(album: Album) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, AlbumDetailsFragment.newInstance(album))
            .commit()
    }

}

