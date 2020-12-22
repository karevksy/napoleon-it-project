package com.karevsky.napoleonit.feature.favorites.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDao
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.album_item.*

class FavoriteAdapter(
    private val onAlbumClick: (Album) -> Unit,
    private val onRemoveClick: (Album) -> Unit,
    private val favoriteDao: FavoriteDao
) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var albums: MutableList<Album> = mutableListOf()

    fun setData() {
        this.albums.clear()
        this.albums.addAll(favoriteDao.getAll())
        notifyDataSetChanged()
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
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

        holder.imgSetFav.setOnClickListener {
            onRemoveClick(item)
        }
    }

    override fun getItemCount(): Int = albums.size
}