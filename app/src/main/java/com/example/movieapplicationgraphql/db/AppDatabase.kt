package com.example.movieapplicationgraphql.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.movieapplicationgraphql.db.dao.TvShowDao
import com.example.movieapplicationgraphql.model.TVShow

@Database(entities = [TVShow::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTVShowDao(): TvShowDao

    companion object {
        @Volatile
        private var DB_INSTANCE: AppDatabase? = null

        fun getAppDBInstance(context: Context): AppDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "DB_TV_SHOWS"
                ).allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}