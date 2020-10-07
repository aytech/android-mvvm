package com.oleg.androidmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oleg.androidmvvm.data.db.MovieDatabase

open class BaseActivity : AppCompatActivity() {

    protected lateinit var movieDataSource: MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDataSource = MovieDatabase.getInstance(this)
    }
}