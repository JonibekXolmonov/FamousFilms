package com.example.movieapplicationgraphql.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapplicationgraphql.model.TVShow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM movie")
    suspend fun getTVShowsFromDB(): List<TVShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShowToDB(tvShow: TVShow)

    @Query("DELETE FROM movie")
    suspend fun deleteTvShowsFromDB()

}