package com.karevsky.napoleonit.feature.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.domain.Genre
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.genres_item.*

class SearchGenresAdapter(
    private val onItemClick: (Genre) -> Unit
) : ListAdapter<Genre, SearchGenresAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.name == newItem.name
    }
}) {

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genres_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.apply {
            tvGenre.text = item.name
            Picasso.get()
                .load(item.srcImage)
                .into(imgGenre)

            containerView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

}