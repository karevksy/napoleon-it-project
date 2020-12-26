package com.karevsky.napoleonit.feature.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
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
) : ListAdapter<Genre, SearchGenresAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean =
        oldItem.name == newItem.name

    //Указываю id, из-за того, что при запросе с API одинаковые жанры имеют разные ссылки
    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean =
        oldItem.id == newItem.id
}) {

    private var unfilteredList = listOf<Genre>()

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

    /**
     * Устанавливает [list] в [SearchGenresAdapter]
     */
    fun modifyList(list: List<Genre>) {
        unfilteredList = list
        submitList(list)
    }

    /**
     * Фильтрация списка, в  зависимости от [query]
     */
    fun filter(query: CharSequence?) {
        val list = mutableListOf<Genre>()

        if (!query.isNullOrEmpty()) {
            list.addAll(unfilteredList.filter {
                it.name.toLowerCase().contains(query.toString().toLowerCase())
            })
        } else {
            list.addAll(unfilteredList)
        }
        submitList(list)
    }
}
