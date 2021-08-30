package com.mfahimi.tvshowmanager.ui.adapter

import android.graphics.Typeface
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfahimi.domain.formatter.DateFormatter
import com.mfahimi.domain.model.output.TvShowData
import com.mfahimi.tvshowmanager.R
import com.mfahimi.tvshowmanager.databinding.ItemMovieBinding
import com.mfahimi.tvshowmanager.ext.SpanModel
import com.mfahimi.tvshowmanager.ext.string
import com.mfahimi.tvshowmanager.ext.stringListToSpan

class MoviesAdapter(
    private val movies: ArrayList<TvShowData>,
    private val dateFormatter: DateFormatter
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var onEndOfListReached: (() -> Unit)? = null
    var isLoading = false

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        val formattedDate = if (movie.releaseDate != null) dateFormatter.formatToDateTime(movie.releaseDate!!) else ""

        holder.binding.tvTitle.text = movie.title
        holder.binding.tvReleaseDate.text = stringListToSpan(
            SpanModel(holder.itemView.string(R.string.msg_release_date), StyleSpan(Typeface.BOLD)),
            SpanModel(formattedDate, StyleSpan(Typeface.NORMAL))
        )
        holder.binding.tvSeasons.text = stringListToSpan(
            SpanModel(holder.itemView.string(R.string.msg_seasons), StyleSpan(Typeface.BOLD)),
            SpanModel(movie.seasons?.toInt().toString(), StyleSpan(Typeface.NORMAL))
        )

        if (position == movies.size - 1 && !isLoading) {
            onEndOfListReached?.invoke()
        }
    }
}