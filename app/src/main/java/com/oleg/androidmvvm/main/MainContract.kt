package com.oleg.androidmvvm.main

import com.oleg.androidmvvm.data.model.Movie

class MainContract {
    interface PresenterInterface {
        fun getMyMoviesList()
        fun onDeleteTapped(selectedMovies: HashSet<Movie>)
        fun stop()
    }
    interface ViewInterface {
        fun displayError(error: Throwable)
        fun displayMessage(message: String)
        fun displayMovies(movies: List<Movie>)
        fun displayNoMovie()
        fun onRemoveSelected(size: Int)
        fun onMoviesLoadComplete()
    }
}
