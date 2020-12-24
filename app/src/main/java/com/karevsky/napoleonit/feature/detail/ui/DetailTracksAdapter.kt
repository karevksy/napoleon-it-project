package com.karevsky.napoleonit.feature.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.domain.AlbumDetails
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.track_item.*

class DetailTracksAdapter() : ListAdapter<AlbumDetails, DetailTracksAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<AlbumDetails>() {
    override fun areItemsTheSame(oldItem: AlbumDetails, newItem: AlbumDetails): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AlbumDetails, newItem: AlbumDetails): Boolean {
        return oldItem.title == newItem.title
    }
}) {

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.apply {
            tvTrackName.text = item.title
            tvTrackArtist.text = item.artist
            tvTrackDuration.text = item.duration.toString()

        }
    }
}