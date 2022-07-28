package com.example.movieapplicationgraphql.utils

import android.util.Log

object Logger {
    fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    fun v(tag: String, msg: String) {
        Log.v(tag, msg)
    }

    fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }
}