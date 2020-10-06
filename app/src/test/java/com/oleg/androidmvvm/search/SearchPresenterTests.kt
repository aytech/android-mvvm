package com.oleg.androidmvvm.search

import com.oleg.androidmvvm.BaseTest
import com.oleg.androidmvvm.RxImmediateSchedulerRule
import com.oleg.androidmvvm.data.model.Movie
import com.oleg.androidmvvm.model.RemoteDataSource
import com.oleg.androidmvvm.model.TmdbResponse
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTests : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mockActivity: SearchContract.ViewInterface

    @Mock
    private val mockDataSource = RemoteDataSource()

    private lateinit var searchPresenter: SearchPresenter

    private val dummyResponse: TmdbResponse
        get() {
            val dummyMovieList = ArrayList<Movie>()
//            dummyMovieList.add(Movie("Title1", "ReleaseDate1", "PosterPath1"))
//            dummyMovieList.add(Movie("Title2", "ReleaseDate2", "PosterPath2"))
//            dummyMovieList.add(Movie("Title3", "ReleaseDate3", "PosterPath3"))
//            dummyMovieList.add(Movie("Title4", "ReleaseDate4", "PosterPath4"))
            return TmdbResponse(page = 1, totalResults = 4, totalPages = 5, dummyMovieList)
        }

    @Before
    fun setUp() {
        searchPresenter = SearchPresenter(viewInterface = mockActivity, dataSource = mockDataSource)
    }

    @Test
    fun testSearchMovie() {
        val response = dummyResponse
        Mockito.doReturn(Observable.just(response)).`when`(mockDataSource)
            .searchResultsObservable(anyString())

        searchPresenter.getSearchResults("The Lion King")

        Mockito.verify(mockActivity).displayResult(response)
    }

    @Test
    fun testSearchMovieError() {
        Mockito.doReturn(Observable.error<Throwable>(Throwable("Error fetching movies")))
            .`when`(mockDataSource).searchResultsObservable(anyString())

        searchPresenter.getSearchResults("The Lion King")

        Mockito.verify(mockActivity).displayError("Error fetching movies")
    }
}