package com.example.movieapplicationgraphql.di

import android.app.Application
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.movieapplicationgraphql.db.AppDatabase
import com.example.movieapplicationgraphql.db.dao.TvShowDao
import com.example.movieapplicationgraphql.networking.GraphQL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val BASE_URL = "https://ql-movie-api.herokuapp.com/graphql"

    /*
   GraphQL Related
    */
    @Provides
    @Singleton
    fun service() = GraphQL

    /*
    Room Related
     */

    @Provides
    @Singleton
    fun appDatabase(context: Application): AppDatabase {
        return AppDatabase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun tvShowDao(appDatabase: AppDatabase): TvShowDao {
        return appDatabase.getTVShowDao()
    }
}