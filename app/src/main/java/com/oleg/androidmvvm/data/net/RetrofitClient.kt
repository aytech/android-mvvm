package com.oleg.androidmvvm.data.net

import com.oleg.androidmvvm.Configuration.Companion.TMDB_API_KEY
import com.oleg.androidmvvm.Configuration.Companion.TMDB_BASE_URL
import com.oleg.androidmvvm.data.model.TmdbResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val moviesApi: MoviesApi

    init {
        val okHttpClient = OkHttpClient.Builder().build()
        moviesApi = Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MoviesApi::class.java)
    }

    fun searchMovies(query: String): Call<TmdbResponse> {
        return moviesApi.searchMovie(TMDB_API_KEY, query)
    }

}