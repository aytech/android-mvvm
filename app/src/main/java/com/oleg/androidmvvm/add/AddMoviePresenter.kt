package com.oleg.androidmvvm.add

import com.oleg.androidmvvm.data.db.MovieDatabase

class AddMoviePresenter(
    private var viewInterface: AddMovieContract.ViewInterface,
    private var movieDataSource: MovieDatabase
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