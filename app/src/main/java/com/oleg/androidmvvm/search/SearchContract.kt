package com.oleg.androidmvvm.search

import com.oleg.androidmvvm.data.model.TmdbResponse

class SearchContract {
    interface PresenterInterface {
        fun getSearchResults(query: String)
        fun stop()
    }

    interface ViewInterface {
        fun displayError(error: String)
        fun displayResult(tmdbResponse: TmdbResponse)
        fun showToast(string: String)
    }
}