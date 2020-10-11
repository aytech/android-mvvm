package com.oleg.androidmvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.oleg.androidmvvm.data.MovieRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddViewModelTest {
    private lateinit var addViewModel: AddViewModel

    @Mock
    lateinit var movieRepository: MovieRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        addViewModel = AddViewModel(movieRepository)
    }

    @Test
    fun cannotSaveMovieWithoutTitle() {
        addViewModel.title.set("")
        addViewModel.releaseDate.set("")
        Assert.assertEquals(false, addViewModel.canSaveMovie())
    }

    @Test
    fun cannotSaveMovieWithoutDate() {
        addViewModel.title.set("Movie")
        addViewModel.releaseDate.set("")
        Assert.assertEquals(false, addViewModel.canSaveMovie())
    }

    @Test
    fun movieSaved() {
        addViewModel.title.set("Movie")
        addViewModel.releaseDate.set("2020")
        addViewModel.saveMovie()
        Assert.assertEquals(true, addViewModel.getSaveLiveData().value)
    }
}