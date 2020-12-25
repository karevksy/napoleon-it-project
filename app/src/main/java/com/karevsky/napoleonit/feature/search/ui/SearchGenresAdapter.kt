package com.karevsky.napoleonit.feature.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.domain.Genre
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.genres_item.*

class SearchGenresAdapter(
    private val onItemClick: (Genre) -> Unit
) : RecyclerView.Adapter<SearchGenresAdapter.ViewHolder>(), Filterable {

    private val genres: MutableList<Genre> = mutableListOf()
    private val genresFull: MutableList<Genre> = genres

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genres_item, parent, false)
        )
    }

    fun setData(genres: List<Genre>) {
        this.genres.clear()
        this.genres.addAll(genres)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = genres[position]

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

    override fun getItemCount(): Int = genres.size

    override fun getFilter(): Filter {
        return genresFilter
    }

    private val genresFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Genre>()

            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(genresFull)
            } else {
                val stringPattern = constraint.toString().toLowerCase().trim()

                for (genre in genresFull) {
                    if (genre.name.toLowerCase().contains(stringPattern)) {
                        filteredList.add(genre)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            genres.clear()
            genres.addAll(results?.values as List<Genre>)
            notifyDataSetChanged()
        }
    }
}
