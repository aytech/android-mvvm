package com.oleg.androidmvvm.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.oleg.androidmvvm.Configuration.Companion.ADD_MOVIE_ACTIVITY_REQUEST_CODE
import com.oleg.androidmvvm.R
import com.oleg.androidmvvm.view.adapters.MovieListAdapter
import com.oleg.androidmvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class MainActivity : BaseActivity() {

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view as Toolbar }

    private val viewAdapter = MovieListAdapter(mutableListOf())
    private lateinit var viewModel: MainViewModel

    private fun deleteMoviesClicked() {
        for (movie in viewAdapter.selectedMovies) {
            viewModel.deleteSavedMovie(movie)
        }
    }

    private fun showLoading() {
        movies_recyclerview.isEnabled = false
        progress_bar.visibility = VISIBLE
    }

    private fun hideLoading() {
        movies_recyclerview.isEnabled = true
        progress_bar.visibility = GONE
    }

    private fun goToAddMovieActivity() {
        val intent = Intent(this@MainActivity, AddMovieActivity::class.java)
        startActivityForResult(intent, ADD_MOVIE_ACTIVITY_REQUEST_CODE)
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        movies_recyclerview.apply { adapter = viewAdapter }
        showLoading()
        viewModel.getSavedMovies().observe(this, { movies ->
            hideLoading()
            movies?.let { viewAdapter.setMovies(movies) }
        })
        fab_add.setOnClickListener { goToAddMovieActivity() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> this.deleteMoviesClicked()
            else -> Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}