package com.karevsky.napoleonit.feature.detail.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import com.karevsky.napoleonit.di.albumApi
import com.karevsky.napoleonit.domain.AlbumDetails
import com.karevsky.napoleonit.domain.GetAlbumByIdUseCase
import com.karevsky.napoleonit.feature.detail.presenter.DetailPresenter
import com.karevsky.napoleonit.feature.detail.presenter.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AlbumDetailsFragment : MvpAppCompatFragment(R.layout.fragment_album_details), DetailView {

    private val presenter: DetailPresenter by moxyPresenter {
        DetailPresenter(
            album = arguments?.getParcelable(ALBUM)!!,
            favoriteDao = FavoriteDaoImpl(
                requireContext().getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )
            ),
            getAlbumByIdUseCase = GetAlbumByIdUseCase(
                albumsApi = albumApi,
                albumId = arguments?.getParcelable<Album>(ALBUM)!!.id
            )
        )
    }

    companion object {
        private const val ALBUM = "ALBUM"
        fun newInstance(album: Album) =
            AlbumDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ALBUM, album)
                }
            }
    }

    private var tracksAdapter: DetailTracksAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgDetailsSetFav.setOnClickListener {
            presenter.onFavClicked()
        }

        with(rvTracks) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = DetailTracksAdapter().also {
                tracksAdapter = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tracksAdapter = null
    }

    override fun setDetails(album: Album, details: List<AlbumDetails>) {
        tvDetailsAlbumName.text = album.name
        tvDetailsBandName.text = album.band
        Picasso.get().load(album.imgSourceBig).into(imgDetailsAlbum)
        tracksAdapter?.submitList(details)
    }

    override fun setIsInFavorites(inFavorites: Boolean) {
        if (inFavorites) imgDetailsSetFav.setImageResource(R.drawable.ic_fav_on)
        else imgDetailsSetFav.setImageResource(R.drawable.ic_fav_off)
    }

    override fun showLoad(isShow: Boolean) {
        detailsProgress.isVisible = isShow
    }
}