package com.oleg.androidmvvm.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.oleg.androidmvvm.Configuration.Companion.SEARCH_QUERY
import com.oleg.androidmvvm.R
import com.oleg.androidmvvm.action
import com.oleg.androidmvvm.data.model.Movie
import com.oleg.androidmvvm.view.adapters.SearchListAdapter
import com.oleg.androidmvvm.snack
import com.oleg.androidmvvm.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search_movie.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class SearchActivity : BaseActivity() {

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view as Toolbar }
    private val viewAdapter =
        SearchListAdapter(mutableListOf()) { movie -> displayConfirmation(movie) }
    private lateinit var viewModel: SearchViewModel
    private lateinit var query: String

    private fun showLoading() {
        searchProgressBar.visibility = VISIBLE
        search_results_recyclerview.isEnabled = false
    }

    private fun hideLoading() {
        searchProgressBar.visibility = GONE
        search_results_recyclerview.isEnabled = true
    }

    private fun showMessage() {
        searchLayout.snack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE) {
            action(getString(R.string.ok)) {
                searchMovie()
            }
        }
    }

    private fun displayConfirmation(movie: Movie) {
        searchLayout.snack("Add ${movie.title} to your list?", Snackbar.LENGTH_LONG) {
            action(getString(R.string.ok)) {
                viewModel.saveMovie(movie)
                val intent = Intent(this@SearchActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun searchMovie() {
        showLoading()
        viewModel.searchMovies(query).observe(this, { movies ->
            hideLoading()
            if (movies == null) {
                showMessage()
            } else {
                viewAdapter.setMovies(movies)
            }
        })
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        intent?.extras?.getString(SEARCH_QUERY)?.let { query = it }
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        search_results_recyclerview.apply { adapter = viewAdapter }
        searchMovie()
    }
}
