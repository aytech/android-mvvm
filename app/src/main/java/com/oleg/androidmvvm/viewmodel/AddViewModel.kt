package com.oleg.androidmvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleg.androidmvvm.data.MovieRepository
import com.oleg.androidmvvm.data.MovieRepositoryImpl
import com.oleg.androidmvvm.data.model.Movie

class AddViewModel(private val repository: MovieRepository = MovieRepositoryImpl()) : ViewModel() {
    var title = ObservableField("")
    var releaseDate = ObservableField("")

    private val saveLiveData = MutableLiveData<Boolean>()

    fun getSaveLiveData(): LiveData<Boolean> = saveLiveData

    fun saveMovie() {
        if (canSaveMovie()) {
            repository.saveMovie(Movie(title = title.get(), releaseDate = releaseDate.get()))
            saveLiveData.postValue(true)
        } else {
            saveLiveData.postValue(false)
        }
    }

    fun canSaveMovie(): Boolean {
        val title = this.title.get()
        val releaseDate = this.releaseDate.get()

        return !title.isNullOrEmpty() && !releaseDate.isNullOrEmpty()
    }
}