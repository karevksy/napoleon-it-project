package com.karevsky.napoleonit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val ALBUM = "ALBUM"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)
        
        bind.btnAdd.setOnClickListener {
            val userAlbum = Album(
                bind.etAlbumEnter.text.toString(),
                bind.etBandEnter.text.toString(),
                bind.etTracksEnter.text.toString().toInt())

            val intent = Intent(this, AlbumDescriptionActivity::class.java)
            intent.putExtra(ALBUM, userAlbum)
            startActivity(intent)
        }
    }
}