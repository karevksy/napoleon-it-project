package com.karevsky.napoleonit.feature.topAlbums.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karevsky.napoleonit.Album
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDao
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.album_item.*

class TopAlbumsAdapter(
    private val onAlbumClick: (Album) -> Unit,
    private val onSetFavClick: (Album) -> Unit,
    private val favoriteDao: FavoriteDao
) :
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
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = albums[position]

        holder.tvAlbumName.text = item.name
        holder.tvArtistName.text = item.band
        holder.tvSongAmount.text = "${item.tracks} треков"
        setImg(item, holder)

        holder.containerView.setOnClickListener {
            onAlbumClick(item)
        }

        holder.imgSetFav.setOnClickListener {
            onSetFavClick(item)
            setImg(item, holder)
        }
    }

    override fun getItemCount(): Int = albums.size

    /**
     * Устанавливает картинку [imgSetFav] в зависимости от того,
     * находится ли [item] в избранном
     */
    private fun setImg(item: Album, holder: ViewHolder) {
        if (favoriteDao.isInFavorites(item)) {
            holder.imgSetFav.setImageResource(R.drawable.ic_fav_on)
        } else holder.imgSetFav.setImageResource(R.drawable.ic_fav_off)
    }
}