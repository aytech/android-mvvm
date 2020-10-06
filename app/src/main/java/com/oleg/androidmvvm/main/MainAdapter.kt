package com.oleg.androidmvvm.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleg.androidmvvm.databinding.ItemMovieMainBinding
import com.oleg.androidmvvm.data.model.Movie
import kotlinx.android.synthetic.main.item_movie_main.view.*

class MainAdapter(private var movieModels: List<Movie>) :
    RecyclerView.Adapter<MainAdapter.MoviesHolder>() {

    internal val selectedMovies = HashSet<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieMainBinding.inflate(inflater, parent, false)
        return MoviesHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) =
        holder.bind(movieModel = movieModels[position])

    override fun getItemCount(): Int = movieModels.size

    private fun selectMovie(position: Int) {
        val movie = movieModels[position]
        if (selectedMovies.contains(movie)) {
            selectedMovies.remove(movie)
        } else {
            selectedMovies.add(movie)
        }
    }

    fun update(movies: List<Movie>) {
        this.movieModels = movies.map {
            Movie(
                title = it.title,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                checked = false
            )
        }
        notifyDataSetChanged()
    }

    fun removeSelected() {
        selectedMovies.clear()
    }

    inner class MoviesHolder(private val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieModel: Movie) {
            binding.movie = movieModel
            binding.root.checkbox.setOnClickListener { selectMovie(adapterPosition) }
        }
    }
}
