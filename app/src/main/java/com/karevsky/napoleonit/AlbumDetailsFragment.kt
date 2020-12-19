package com.karevsky.napoleonit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_album_details.*

class AlbumDetailsFragment : Fragment(R.layout.fragment_album_details) {

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

        arguments?.let {
            val album = it.getParcelable<Album>(ALBUM)

            tvDetailsAlbumName.text = album?.name
            tvDetailsBandName.text = "Исполнитель: ${album?.band}"
            tvDetailsSongsAmount.text = "${album?.tracks} треков"

        }
    }


}