package com.oleg.androidmvvm.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.oleg.androidmvvm.BaseActivity
import com.oleg.androidmvvm.Configuration.Companion.EXTRA_POSTER_PATH
import com.oleg.androidmvvm.Configuration.Companion.EXTRA_RELEASE_DATE
import com.oleg.androidmvvm.Configuration.Companion.EXTRA_TITLE
import com.oleg.androidmvvm.Configuration.Companion.SEARCH_MOVIE_ACTIVITY_REQUEST_CODE
import com.oleg.androidmvvm.Configuration.Companion.SEARCH_QUERY
import com.oleg.androidmvvm.Configuration.Companion.TMDB_IMAGE_URL
import com.oleg.androidmvvm.R
import com.oleg.androidmvvm.add.AddMovieContract
import com.oleg.androidmvvm.add.AddMoviePresenter
import com.oleg.androidmvvm.search.SearchActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add.*

class AddMovieActivity : BaseActivity(), AddMovieContract.ViewInterface {

    private lateinit var addMoviePresenter: AddMovieContract.PresenterInterface

    private fun goToSearchMovieActivity() {
        if (movie_title.text.isNullOrEmpty()) {
            showToast("Enter query")
        } else {
            val intent = Intent(this@AddMovieActivity, SearchActivity::class.java)
            intent.putExtra(SEARCH_QUERY, movie_title.text.toString())
            startActivityForResult(intent, SEARCH_MOVIE_ACTIVITY_REQUEST_CODE)
        }
    }

    private fun onClickAddMovie() {
        val title: String = movie_title.text.toString()
        val releaseDate: String = movie_release_date.text.toString()
        val posterPath: String = if (movie_image.tag == null) "" else movie_image.tag.toString()
        addMoviePresenter.addMovie(title, releaseDate, posterPath)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        addMoviePresenter = AddMoviePresenter(this, movieDataSource)
        search_button.setOnClickListener { goToSearchMovieActivity() }
        add_movie.setOnClickListener { onClickAddMovie() }
    }

    override fun returnToMain() {
        setResult(RESULT_OK)
        finish()
    }

    override fun showToast(string: String) {
        Toast.makeText(this@AddMovieActivity, string, Toast.LENGTH_LONG).show()
    }

    override fun displayError(string: String) {
        showToast(string)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        this@AddMovieActivity.runOnUiThread {
            movie_title.setText(data?.getStringExtra(EXTRA_TITLE))
            movie_release_date.setText(data?.getStringExtra(EXTRA_RELEASE_DATE))
            movie_image.tag = data?.getStringExtra(EXTRA_POSTER_PATH)
            Picasso.get().load(TMDB_IMAGE_URL + data?.getStringExtra(EXTRA_POSTER_PATH))
                .into(movie_image)
        }
    }
}