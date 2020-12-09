package com.karevsky.napoleonit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.FragmentTopAlbumsBinding

class TopAlbumsFragment : Fragment() {

    private lateinit var  bind : FragmentTopAlbumsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = FragmentTopAlbumsBinding.inflate(layoutInflater)
    }

    //возвращаем bind.root для viewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_top_albums, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentManager = requireFragmentManager()

        bind.btnAdd.setOnClickListener {
            val userAlbum = Album(
                bind.etAlbumEnter.text.toString(),
                bind.etBandEnter.text.toString(),
                bind.etTracksEnter.text.toString().toInt()
            )

            fragmentManager.beginTransaction()
                .replace(R.id.container, DescriptionFragment())
                .addToBackStack("Top Albums")
                .commit()
        }
    }
}