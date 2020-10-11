package com.oleg.androidmvvm.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.oleg.androidmvvm.Configuration.Companion.TMDB_IMAGE_URL
import com.oleg.androidmvvm.R
import com.oleg.androidmvvm.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_search.view.*

class SearchListAdapter(
    private var movies: MutableList<Movie>,
    private var listener: (Movie) -> Unit
) :
    RecyclerView.Adapter<SearchListAdapter.SearchMoviesHolder>() {

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_search, parent, false)
        return SearchMoviesHolder(inflater)
    }

    override fun onBindViewHolder(holder: SearchMoviesHolder, position: Int) =
        holder.bind(movies[position], position)

    override fun getItemCount(): Int = movies.size

    inner class SearchMoviesHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie, position: Int) = with(view) {
            title_text.text = movie.title
            release_date_text.text = movie.releaseDate
            view.setOnClickListener { listener(movies[position]) }
            if (movie.posterPath == null) {
                movie_image.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_local_movies_gray,
                        null
                    )
                )
            } else {
                Picasso.get().load(TMDB_IMAGE_URL + movie.posterPath).into(movie_image)
            }
        }
    }
}
