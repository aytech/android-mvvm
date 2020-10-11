package com.oleg.androidmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oleg.androidmvvm.data.MovieRepository
import com.oleg.androidmvvm.data.MovieRepositoryImpl
import com.oleg.androidmvvm.data.model.Movie

class SearchViewModel(private val repository: MovieRepository = MovieRepositoryImpl()) :
    ViewModel() {

    fun searchMovies(query: String): LiveData<List<Movie>?> {
        return repository.searchMovies(query)
    }

    fun saveMovie(movie: Movie) {
        repository.saveMovie(movie)
    }
}