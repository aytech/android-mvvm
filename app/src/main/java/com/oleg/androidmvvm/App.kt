package com.oleg.androidmvvm

import android.app.Application
import com.oleg.androidmvvm.data.db.MovieDatabase
import timber.log.Timber

lateinit var db: MovieDatabase

@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = MovieDatabase.getInstance(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}