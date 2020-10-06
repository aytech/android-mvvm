package com.oleg.androidmvvm.add

import com.oleg.androidmvvm.LocalDatabase
import com.oleg.androidmvvm.data.model.Movie
import kotlin.concurrent.thread

class AddMoviePresenter(
    private var viewInterface: AddMovieContract.ViewInterface,
    private var localDataSource: LocalDatabase
) : AddMovieContract.PresenterInterface {

    override fun addMovie(title: String, releaseDate: String, posterPath: String) {
        if (title.isEmpty()) {
            viewInterface.displayError("Movie title cannot be empty")
        } else {
            //thread {
                //localDataSource.movieDao().insert(Movie(title, releaseDate, posterPath))
            //}
            viewInterface.returnToMain()
        }
    }
}