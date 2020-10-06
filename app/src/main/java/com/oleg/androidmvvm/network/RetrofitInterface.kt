package com.oleg.androidmvvm.network

import com.oleg.androidmvvm.model.TmdbResponse
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