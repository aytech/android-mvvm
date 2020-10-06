package com.oleg.androidmvp.main

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.times
import com.oleg.androidmvp.BaseTest
import com.oleg.androidmvp.LocalDatabase
import com.oleg.androidmvp.RxImmediateSchedulerRule
import com.oleg.androidmvp.model.Movie
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTests : BaseTest() {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mockActivity: MainContract.ViewInterface

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var mockDataSource: LocalDatabase

    private lateinit var mainPresenter: MainPresenter

    private val dummyMovies: List<Movie>
        get() {
            val dummyMovieList = ArrayList<Movie>()
            dummyMovieList.add(Movie("Title1", "ReleaseDate1", "PosterPath1"))
            dummyMovieList.add(Movie("Title2", "ReleaseDate2", "PosterPath2"))
            dummyMovieList.add(Movie("Title3", "ReleaseDate3", "PosterPath3"))
            dummyMovieList.add(Movie("Title4", "ReleaseDate4", "PosterPath4"))
            return dummyMovieList
        }
    private val deletedHashSetSingle: HashSet<Movie>
        get() {
            val deletedHashSet = HashSet<Movie>()
            deletedHashSet.add(dummyMovies[2])
            return deletedHashSet
        }
    private val deletedHashSetMultiple: HashSet<Movie>
        get() {
            val deletedHashSet = HashSet<Movie>()
            deletedHashSet.add(dummyMovies[1])
            deletedHashSet.add(dummyMovies[3])
            return deletedHashSet
        }

    @Before
    fun setUp() {
        mainPresenter =
            MainPresenter(viewInterface = mockActivity, localDataSource = mockDataSource)
    }

    @Test
    fun testGetMyMoviesList() {
        val movies = dummyMovies
        given(mockDataSource.movieDao().all).willReturn(Observable.just(movies))

        mainPresenter.getMyMoviesList()

        Mockito.verify(mockDataSource.movieDao()).all
        Mockito.verify(mockActivity).displayMovies(movies)
    }

    @Test
    fun testGetMyMoviesListWithNoMovies() {
        given(mockDataSource.movieDao().all).willReturn(Observable.just(ArrayList()))

        mainPresenter.getMyMoviesList()

        Mockito.verify(mockDataSource.movieDao()).all
        Mockito.verify(mockActivity).displayNoMovie()
    }

    @Test
    fun deleteEmpty() {
        mainPresenter.onDeleteTapped(HashSet())

        Mockito.verify(mockActivity).displayMessage("Select a movie")
        Mockito.verifyNoMoreInteractions(mockActivity, mockDataSource)
    }

    @Test
    fun deleteSingle() {
        val deletedHashSet = deletedHashSetSingle

        mainPresenter.onDeleteTapped(deletedHashSet)

        Mockito.verify(mockDataSource.movieDao(), times(1)).delete(null)
        Mockito.verify(mockActivity).onRemoveSelected(1)
    }

    @Test
    fun deleteMultiple() {
        val deletedHashSet = deletedHashSetMultiple

        mainPresenter.onDeleteTapped(deletedHashSet)

        Mockito.verify(mockDataSource.movieDao(), times(2)).delete(null)
        Mockito.verify(mockActivity).onRemoveSelected(2)
    }
}