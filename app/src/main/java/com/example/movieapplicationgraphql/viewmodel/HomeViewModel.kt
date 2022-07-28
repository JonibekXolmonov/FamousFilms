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
class HomeViewModel @Inject constructor(private val tvShowDao: TvShowDao) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val tvShowsDetails = MutableLiveData<PopularMoviesQuery.PopularMovies>()

    fun apiGetPopularMovies() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                tvShowsDetails.value =
                    GraphQL.get().query(PopularMoviesQuery()).execute().data!!.popularMovies
                isLoading.value = false
            } catch (e: ApolloException) {
                isLoading.value = false
                errorMessage.value = e.localizedMessage
            }
        }
    }

    fun saveToDB(tvShow: TVShow) {
        viewModelScope.launch {
            tvShowDao.insertTVShowToDB(tvShow)
        }
    }
}