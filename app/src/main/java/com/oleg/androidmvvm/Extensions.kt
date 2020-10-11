@file:Suppress("unused", "unused")

package com.oleg.androidmvvm

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length)
}

inline fun View.snack(
    message: String,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    snack.action()
    snack.show()
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

@BindingAdapter("tmdbImage")
fun loadTmdbImage(view: ImageView, url: String?) {
    if (url != null) {
        Picasso.get()
            .load(Configuration.TMDB_IMAGE_URL + url)
            .placeholder(R.drawable.ic_local_movies_gray)
            .error(R.drawable.ic_movie_teal_24dp)
            .into(view)
    }
}