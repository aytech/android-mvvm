package com.oleg.androidmvvm.data.net

import com.oleg.androidmvvm.data.model.TmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String): Call<TmdbResponse>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Call<TmdbResponse>
}