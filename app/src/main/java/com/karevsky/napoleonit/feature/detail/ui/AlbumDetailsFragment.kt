package com.karevsky.napoleonit.feature.detail.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import com.karevsky.napoleonit.feature.detail.presenter.DetailPresenter
import com.karevsky.napoleonit.feature.detail.presenter.DetailView
import kotlinx.android.synthetic.main.fragment_album_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AlbumDetailsFragment : MvpAppCompatFragment(R.layout.fragment_album_details), DetailView {

    private val presenter: DetailPresenter by moxyPresenter {
        DetailPresenter(
            arguments?.getParcelable(ALBUM)!!,
            favoriteDao = FavoriteDaoImpl(
                requireContext().getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgDetailsSetFav.setOnClickListener{
            presenter.onFavClicked()
        }
    }

    override fun setDetails(album: Album) {
        tvDetailsAlbumName.text = album.name
        tvDetailsBandName.text = "Исполнитель: ${album.band}"
        tvDetailsSongsAmount.text = "${album.tracks} треков"
    }

    override fun setIsInFavorites(inFavorites: Boolean) {
        if (inFavorites) imgDetailsSetFav.setImageResource(R.drawable.ic_fav_on)
        else imgDetailsSetFav.setImageResource(R.drawable.ic_fav_off)
    }
}