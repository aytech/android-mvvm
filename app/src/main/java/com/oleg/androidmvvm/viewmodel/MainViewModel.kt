package com.oleg.androidmvvm.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.oleg.androidmvvm.data.MovieRepository
import com.oleg.androidmvvm.data.MovieRepositoryImpl
import com.oleg.androidmvvm.data.model.Movie

class MainViewModel(private val repository: MovieRepository = MovieRepositoryImpl()) : ViewModel() {

    private val allMovies = MediatorLiveData<List<Movie>>()

    private fun getAllMovies() {
        allMovies.addSource(repository.getSavedMovies()) { movies -> allMovies.postValue(movies) }
    }

    init {
        getAllMovies()
    }

    fun getSavedMovies() = allMovies

    fun deleteSavedMovie(movie: Movie) {
        repository.deleteMovie(movie = movie)
    }
}