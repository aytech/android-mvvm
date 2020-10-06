package com.oleg.androidmvvm.model

import com.oleg.androidmvvm.Configuration.Companion.TMDB_API_KEY
import com.oleg.androidmvvm.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RemoteDataSource {
    open fun searchResultsObservable(queryString: String): Observable<TmdbResponse> {
        return RetrofitClient.moviesApi
            .searchMovie(api_key = TMDB_API_KEY, query = queryString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}