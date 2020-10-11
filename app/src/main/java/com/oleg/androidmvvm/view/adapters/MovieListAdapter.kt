package com.oleg.androidmvvm.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleg.androidmvvm.databinding.ItemMovieMainBinding
import com.oleg.androidmvvm.data.model.Movie

class MovieListAdapter(private var movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.MoviesHolder>() {

    val selectedMovies = HashSet<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieMainBinding.inflate(inflater, parent, false)
        return MoviesHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val movie = movies[position]
        holder.binding.movie = movie
        holder.binding.checkbox.isChecked = selectedMovies.contains(movie)
        holder.binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (!selectedMovies.contains(movie) && isChecked) {
                selectedMovies.add(movies[position])
            } else {
                selectedMovies.remove(movies[position])
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MoviesHolder(val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root)
}
