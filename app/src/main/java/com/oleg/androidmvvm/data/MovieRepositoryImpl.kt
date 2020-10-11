package com.oleg.androidmvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oleg.androidmvvm.data.model.Movie
import com.oleg.androidmvvm.data.db.MovieDao
import com.oleg.androidmvvm.data.model.TmdbResponse
import com.oleg.androidmvvm.data.net.RetrofitClient
import com.oleg.androidmvvm.db
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.concurrent.thread

class MovieRepositoryImpl : MovieRepository {

    private val movieDao: MovieDao = db.movieDao()
    private val retrofitClient = RetrofitClient()
    private val allMovies: LiveData<List<Movie>>

    init {
        allMovies = movieDao.getAll()
    }

    override fun getSavedMovies(): LiveData<List<Movie>> = allMovies

    override fun saveMovie(movie: Movie) {
        thread {
            movieDao.insert(movie)
        }
    }

    override fun deleteMovie(movie: Movie) {
        thread {
            movieDao.delete(movie.id)
        }
    }

    override fun searchMovies(query: String): LiveData<List<Movie>?> {
        val data = MutableLiveData<List<Movie>>()

        retrofitClient.searchMovies(query).enqueue(object : Callback<TmdbResponse> {
            override fun onResponse(call: Call<TmdbResponse>, response: Response<TmdbResponse>) {
                data.value = response.body()?.results
                Timber.d("Got movies from TMDB: %s", response.body()?.results)
            }

            override fun onFailure(call: Call<TmdbResponse>, error: Throwable) {
                data.value = null
                Timber.d("Error fetching movies from TMDB: $error")
            }
        })

        return data
    }
}