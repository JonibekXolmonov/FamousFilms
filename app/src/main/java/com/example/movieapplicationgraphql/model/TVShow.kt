package com.example.movieapplicationgraphql.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class TVShow(
    @PrimaryKey
    val id: Int,
    val original_title: String? = null,
    val release_date: String? = null,
    val poster_path: String? = null
)
