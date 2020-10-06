package com.oleg.androidmvp.network

import com.oleg.androidmvp.model.TmdbResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Observable<TmdbResponse>
}