package com.oleg.androidmvvm.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.oleg.androidmvvm.Configuration.Companion.SEARCH_MOVIE_ACTIVITY_REQUEST_CODE
import com.oleg.androidmvvm.Configuration.Companion.SEARCH_QUERY
import com.oleg.androidmvvm.R
import com.oleg.androidmvvm.action
import com.oleg.androidmvvm.databinding.ActivityAddBinding
import com.oleg.androidmvvm.snack
import com.oleg.androidmvvm.viewmodel.AddViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class AddMovieActivity : BaseActivity() {

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view as Toolbar }
    private lateinit var viewModel: AddViewModel

    fun goToSearchMovieActivity(view: View) {
        if (movie_title.text.toString().isNotBlank()) {
            val intent = Intent(this@AddMovieActivity, SearchActivity::class.java)
            intent.putExtra(SEARCH_QUERY, movie_title.text.toString())
            startActivityForResult(intent, SEARCH_MOVIE_ACTIVITY_REQUEST_CODE)
        } else {
            showMessage(getString(R.string.enter_title))
        }
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityAddBinding>(this, R.layout.activity_add)
        viewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
        binding.model = viewModel
        configureLiveDataObservers()
    }

    private fun showMessage(message: String) {
        addLayout.snack(message) {
            action(getString(R.string.ok)) {}
        }
    }

    private fun configureLiveDataObservers() {
        viewModel.getSaveLiveData().observe(this, { saved ->
            saved?.let {
                if (saved) {
                    finish()
                } else {
                    showMessage(getString(R.string.title_date_message))
                }
            }
        })
    }
}