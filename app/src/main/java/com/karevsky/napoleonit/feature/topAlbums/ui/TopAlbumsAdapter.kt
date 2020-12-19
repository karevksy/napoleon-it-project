package com.karevsky.napoleonit.feature.topAlbums.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.top_albums_item.*

class TopAlbumsAdapter(private val onAlbumClick: (Album) -> Unit) :
    RecyclerView.Adapter<TopAlbumsAdapter.ViewHolder>() {

    private var albums: MutableList<Album> = mutableListOf()

    fun setData(albums: List<Album>) {
        this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_albums_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = albums[position]
        holder.tvAlbumName.text = item.name
        holder.tvArtistName.text = item.band
        holder.tvSongAmount.text = "${item.tracks} треков"
        holder.containerView.setOnClickListener {
            onAlbumClick(item)
        }

    }

    override fun getItemCount(): Int = albums.size

}