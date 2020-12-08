package com.karevsky.napoleonit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.ActivityAlbumDescriptionBinding
import kotlinx.android.synthetic.main.activity_main.*

class AlbumDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityAlbumDescriptionBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        val album = intent.extras?.getParcelable<Album>("ALBUM")
        bind.let {
            tvAlbumName.text = album?.name
            tvBandName.text = album?.band
            tvTracksAmount.text = "Количество треков в альбоме: ${album?.tracks}"
        }
    }
}