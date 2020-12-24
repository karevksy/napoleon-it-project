package com.karevsky.napoleonit.feature.detail.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.domain.Album
import com.karevsky.napoleonit.domain.AlbumDetails

import com.karevsky.napoleonit.feature.detail.presenter.DetailPresenter
import com.karevsky.napoleonit.feature.detail.presenter.DetailPresenterFactory
import com.karevsky.napoleonit.feature.detail.presenter.DetailView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_album_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailsFragment : MvpAppCompatFragment(R.layout.fragment_album_details), DetailView {

    @Inject
    lateinit var detailPresenterFactory: DetailPresenterFactory

    companion object {
        private const val ALBUM = "ALBUM"
        fun newInstance(album: Album) =
            AlbumDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ALBUM, album)
                }
            }
    }

    private val presenter: DetailPresenter by moxyPresenter {
        detailPresenterFactory.create(
            arguments?.getParcelable(ALBUM)!!
        )
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