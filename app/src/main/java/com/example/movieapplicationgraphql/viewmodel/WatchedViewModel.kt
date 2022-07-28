package com.example.movieapplicationgraphql.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.movieapplicationgraphql.PopularMoviesQuery
import com.example.movieapplicationgraphql.db.dao.TvShowDao
import com.example.movieapplicationgraphql.model.TVShow
import com.example.movieapplicationgraphql.networking.GraphQL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchedViewModel @Inject constructor(private val tvShowDao: TvShowDao) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val tvShowsDetailsDB = MutableLiveData<List<TVShow>>()

    fun dbGetWatchedMovies() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                tvShowsDetailsDB.value = tvShowDao.getTVShowsFromDB()
                isLoading.value = false
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage
                isLoading.value = false
            }
        }
    }
}