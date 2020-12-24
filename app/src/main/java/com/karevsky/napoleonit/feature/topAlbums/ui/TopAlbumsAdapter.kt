package com.karevsky.napoleonit.feature.topAlbums.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.data.FavoriteDao
import com.karevsky.napoleonit.domain.Album
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.album_item.*

class TopAlbumsAdapter(
    private val onAlbumClick: (Album) -> Unit,
    private val onSetFavClick: (Album) -> Unit,
    private val favoriteDao: FavoriteDao
) : ListAdapter<Album, TopAlbumsAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
        oldItem.name == newItem.name
}) {

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.apply {
            tvAlbumName.text = item.name
            tvArtistName.text = item.band
            setImg(item, this)

            Picasso.get()
                .load(item.imgSource)
                .into(albumImg)

            containerView.setOnClickListener {
                onAlbumClick(item)
            }

            imgSetFav.setOnClickListener {
                onSetFavClick(item)
                setImg(item, this)
            }
        }

    }

    /**
     * Устанавливает картинку для [imgSetFav] в зависимости от того,
     * находится ли [item] в избранном
     */
    private fun setImg(item: Album, holder: ViewHolder) {
        if (favoriteDao.isInFavorites(item)) {
            holder.imgSetFav.setImageResource(R.drawable.ic_fav_on)
        } else holder.imgSetFav.setImageResource(R.drawable.ic_fav_off)
    }
}