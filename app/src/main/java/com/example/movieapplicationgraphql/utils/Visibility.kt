package com.example.movieapplicationgraphql.utils

import android.view.View

object Visibility {
    fun View.hide() {
        this.visibility = View.GONE
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }
}